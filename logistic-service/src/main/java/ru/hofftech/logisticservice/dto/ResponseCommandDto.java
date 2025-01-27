package ru.hofftech.logisticservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseCommandDto {
    private String resultCommandExecuted;
}
