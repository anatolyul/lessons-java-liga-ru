package ru.hofftech.logisticservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import ru.hofftech.logisticservice.dto.OutboxEventDto;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillingStreamService {
    private final StreamBridge streamBridge;
    private static final String RESPONSE_QUEUE = "billing-out-0";

    public void publish(OutboxEventDto outboxEventDto) {
        Message<OutboxEventDto> message = MessageBuilder.withPayload(outboxEventDto)
                .setHeader(KafkaHeaders.KEY, outboxEventDto.getIdempotentKey().toString().getBytes(StandardCharsets.UTF_8))
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();

        streamBridge.send(RESPONSE_QUEUE, message);

        log.debug("Published payload {}", outboxEventDto);
    }
}
