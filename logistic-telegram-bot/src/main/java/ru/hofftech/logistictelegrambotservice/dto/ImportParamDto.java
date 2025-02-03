package ru.hofftech.logistictelegrambotservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ImportParamDto {

    private String filename;
}
