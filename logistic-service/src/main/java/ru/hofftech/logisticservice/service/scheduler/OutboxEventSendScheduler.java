package ru.hofftech.logisticservice.service.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.hofftech.logisticservice.entity.OutboxEventEntity;
import ru.hofftech.logisticservice.mapper.OutboxEventMapper;
import ru.hofftech.logisticservice.model.enums.OrderOutboxStatus;
import ru.hofftech.logisticservice.repository.OutboxEventRepository;
import ru.hofftech.logisticservice.service.BillingStreamService;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxEventSendScheduler {

    private final OutboxEventRepository outboxEventRepository;
    private final BillingStreamService billingStreamService;
    private final OutboxEventMapper outboxEventMapper;

    @Scheduled(fixedRate = 1000)
    public void eventsFindAndSend() {
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
}
