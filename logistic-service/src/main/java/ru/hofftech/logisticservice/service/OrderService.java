package ru.hofftech.logisticservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.dto.OrderDto;
import ru.hofftech.logisticservice.dto.OutboxEventDto;
import ru.hofftech.logisticservice.entity.OrderEntity;
import ru.hofftech.logisticservice.entity.OutboxEventEntity;
import ru.hofftech.logisticservice.mapper.OrderMapper;
import ru.hofftech.logisticservice.mapper.OutboxEventMapper;
import ru.hofftech.logisticservice.model.Truck;
import ru.hofftech.logisticservice.model.enums.OrderOutboxStatus;
import ru.hofftech.logisticservice.model.enums.TypeOrderProcess;
import ru.hofftech.logisticservice.repository.OrderRepository;
import ru.hofftech.logisticservice.repository.OutboxEventRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OutboxEventRepository outboxEventRepository;
    private final OutboxEventMapper outboxEventMapper;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BillingStreamService billingStreamService;

    @Transactional
    public void saveOrder(OrderDto orderDto) {

        OrderEntity orderEntity = orderMapper.toEntity(orderDto);
        orderRepository.save(orderEntity);

        OutboxEventDto outboxEventDto = OutboxEventDto.builder()
                .idempotentKey(UUID.randomUUID())
                .order(orderDto)
                .status(OrderOutboxStatus.CREATED)
                .build();

        OutboxEventEntity outboxEventEntity = outboxEventMapper.toEntity(outboxEventDto);
        outboxEventEntity.setOrder(orderEntity);
        outboxEventRepository.save(outboxEventEntity);
    }

    public OrderDto createOrderDto(String clientName, TypeOrderProcess type, LocalDate date, List<Truck> trucks) {
        Integer totalSegmentCount = calculateTotalSegments(trucks);
        return OrderDto.builder()
                .clientName(clientName)
                .type(type)
                .date(date)
                .boxCount(trucks.stream()
                        .filter(truck -> truck.getBoxes() != null)
                        .mapToInt(truck -> truck.getBoxes().size())
                        .sum())
                .truckCount(trucks.size())
                .segmentCount(totalSegmentCount)
                .build();
    }

    public void sendOrders() {
        List<OutboxEventEntity> outboxEvents = outboxEventRepository.findAllByStatus(OrderOutboxStatus.CREATED);
        List<OutboxEventEntity> outboxEventsResult = new ArrayList<>();

        outboxEvents.forEach(outboxEvent -> {
                    try {
                        billingStreamService.publish(outboxEventMapper.toDto(outboxEvent));
                        outboxEvent.setStatus(OrderOutboxStatus.DONE);
                    } catch (Exception e) {
                        outboxEvent.setStatus(OrderOutboxStatus.FAILED);
                        log.error(e.getMessage(), e);
                    } finally {
                        outboxEventsResult.add(outboxEvent);
                    }
                }
        );

        outboxEventRepository.saveAll(outboxEventsResult);
    }

    private Integer calculateTotalSegments(List<Truck> trucks) {
        return trucks.stream()
                .filter(truck -> truck.getBoxes() != null)
                .flatMap(truck -> truck.getBoxes().stream())
                .mapToInt(BoxDto::calculateSegments)
                .sum();
    }
}
