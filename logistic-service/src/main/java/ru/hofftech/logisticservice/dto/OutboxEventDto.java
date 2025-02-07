package ru.hofftech.logisticservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hofftech.logisticservice.model.enums.OrderOutboxStatus;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutboxEventDto {

    private UUID idempotentKey;
    private OrderDto order;

    @JsonIgnore
    private OrderOutboxStatus status;
}
