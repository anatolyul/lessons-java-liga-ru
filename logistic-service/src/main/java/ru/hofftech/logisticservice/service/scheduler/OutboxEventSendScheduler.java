package ru.hofftech.logisticservice.service.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.hofftech.logisticservice.service.OrderService;

@Component
@RequiredArgsConstructor
public class OutboxEventSendScheduler {

    private final OrderService orderService;

    @Scheduled(fixedRate = 1000)
    public void send() {
        orderService.sendOrders();
    }
}

