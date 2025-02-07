package ru.hofftech.logisticbilling.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import ru.hofftech.logisticbilling.dto.InboxEventDto;
import ru.hofftech.logisticbilling.service.BillingService;

import java.util.function.Consumer;

@Configuration
public class ConsumerConfig {

    /**
     * Имя бина должно совпадать с каналом в пропертях: spring.cloud.stream.bindings.{beanName}-in-0
     * Имя бина должно быть в spring.cloud.stream.function.definition
     */
    @Bean
    public Consumer<Message<InboxEventDto>> billing(BillingService billingService) {
        return message ->
                billingService.createOrder(message.getPayload());
    }
}
