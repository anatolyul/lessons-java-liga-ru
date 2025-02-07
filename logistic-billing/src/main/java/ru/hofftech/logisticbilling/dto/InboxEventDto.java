package ru.hofftech.logisticbilling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InboxEventDto {

    private UUID idempotentKey;
    private OrderDto order;
}
