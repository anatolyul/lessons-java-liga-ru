package ru.hofftech.logistictelegrambotservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.hofftech.logistictelegrambotservice.enums.TypeAlgorithm;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class LoadParamDto {

    private String clientName;

    private LocalDate date;

    private String parcelsText;

    private String parcelsFile;

    private String trucks;

    private TypeAlgorithm type;

    private String outFilename;
}
