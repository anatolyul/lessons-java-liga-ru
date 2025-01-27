package ru.hofftech.logisticservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Параметры загрузки посылок по именам в машины")
public class LoadParamDto {

    @Schema(description = "Текст с именами посылок")
    private String parcelsText;

    @Schema(description = "Файл с именами посылок")
    private String parcelsFile;

    @Schema(description = "Размеры машин")
    private String trucks;

    @Schema(description = "Тип алгоритма погрузки")
    private String type;

    @Schema(description = "Имя выходного файла")
    private String outFilename;
}
