package ru.hofftech.logistictelegrambotservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoadParamDto {

    private String parcelsText;

    private String parcelsFile;

    private String trucks;

    private String type;

    private String outFilename;
}
