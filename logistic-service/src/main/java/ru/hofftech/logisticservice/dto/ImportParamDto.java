package ru.hofftech.logisticservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Параметры импорта посылок из файла txt/json")
public class ImportParamDto {

    @Schema(description = "Файл импорта данных")
    private String filename;
}
