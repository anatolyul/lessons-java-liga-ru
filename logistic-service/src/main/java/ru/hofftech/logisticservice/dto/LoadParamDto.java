package ru.hofftech.logisticservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import ru.hofftech.logisticservice.model.enums.TypeAlgorithm;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Параметры загрузки посылок по именам в машины")
public class LoadParamDto {

    @Schema(description = "Идентификатор клиента")
    private String clientName;

    @Schema(description = "Дата операции")
    private LocalDate date;

    @Schema(description = "Текст с именами посылок")
    private String parcelsText;

    @Schema(description = "Файл с именами посылок")
    private String parcelsFile;

    @Schema(description = "Размеры машин")
    private String trucks;

    @Schema(description = "Тип алгоритма погрузки")
    private TypeAlgorithm type;

    @Schema(description = "Имя выходного файла")
    private String outFilename;
}
