package ru.hofftech.logisticbilling.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import ru.hofftech.logisticbilling.dto.InboxEventDto;
import ru.hofftech.logisticbilling.service.BillingService;

import java.util.function.Consumer;

@Configuration
public class ConsumerConfig {

    @Bean
    public Consumer<Message<InboxEventDto>> billing(BillingService billingService) {
        return message ->
                billingService.createOrder(message.getPayload());
    }
}
