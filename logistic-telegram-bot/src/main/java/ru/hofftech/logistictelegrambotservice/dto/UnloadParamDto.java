package ru.hofftech.logistictelegrambotservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UnloadParamDto {

    private String clientName;

    private LocalDate date;

    private String inFilename;

    private String outFilename;

    private boolean withCount;
}
