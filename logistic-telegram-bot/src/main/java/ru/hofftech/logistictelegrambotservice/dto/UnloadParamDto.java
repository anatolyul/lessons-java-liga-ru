package ru.hofftech.logistictelegrambotservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnloadParamDto {

    private String inFilename;

    private String outFilename;

    private boolean withCount;
}
