package ru.hofftech.logisticbilling.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hofftech.logisticbilling.config.BillingConfig;
import ru.hofftech.logisticbilling.dto.InboxEventDto;
import ru.hofftech.logisticbilling.dto.OrderDto;
import ru.hofftech.logisticbilling.entity.InboxEventEntity;
import ru.hofftech.logisticbilling.entity.OrderEntity;
import ru.hofftech.logisticbilling.mapper.InboxEventMapper;
import ru.hofftech.logisticbilling.mapper.OrderMapper;
import ru.hofftech.logisticbilling.model.enums.TypeOrderProcess;
import ru.hofftech.logisticbilling.repository.InboxEventRepository;
import ru.hofftech.logisticbilling.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillingService {

    private final OrderRepository orderRepository;
    private final InboxEventRepository inboxEventRepository;
    private final InboxEventMapper inboxEventMapper;
    private final OrderMapper orderMapper;
    private final BillingConfig billingConfig;

    @Transactional
    public void createOrder(InboxEventDto inboxEventDto) {
        if (inboxEventRepository.existsByIdempotentKey(inboxEventDto.getIdempotentKey())) {
            log.error("Операция уже была обработана ранее по ключу {} !", inboxEventDto.getIdempotentKey());
            return;
        }

        OrderDto orderDto = inboxEventDto.getOrder();
        orderDto.setAmount(calculatedAmount(inboxEventDto.getOrder()));
        OrderEntity order = orderRepository.save(orderMapper.toEntity(orderDto));
        InboxEventEntity eventEntity = inboxEventMapper.toEntity(inboxEventDto);
        eventEntity.setOrder(order);

        inboxEventRepository.save(eventEntity);
    }

    @Cacheable("orders")
    public Page<OrderDto> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable).map(orderMapper::toDto);
    }

    @Cacheable("orders")
    public List<OrderDto> findByNameWithPeriod(String clientName,
                                               LocalDate startDate,
                                               LocalDate endDate) {
        return orderMapper.toDtoList(
                orderRepository.findAllByClientNameAndDateBetween(clientName, startDate, endDate));
    }

    private BigDecimal calculatedAmount(OrderDto orderDto) {
        return BigDecimal.valueOf(
                orderDto.getSegmentCount() *
                        (orderDto.getType() == TypeOrderProcess.LOAD
                                ? billingConfig.getCostLoad()
                                : billingConfig.getCostUnload()));
    }
}
