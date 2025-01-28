package ru.hofftech.logistictelegrambotservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.hofftech.logistictelegrambotservice.enums.TypeAlgorithm;

@Getter
@Setter
@Builder
public class LoadParamDto {

    private String parcelsText;

    private String parcelsFile;

    private String trucks;

    private TypeAlgorithm type;

    private String outFilename;
}
