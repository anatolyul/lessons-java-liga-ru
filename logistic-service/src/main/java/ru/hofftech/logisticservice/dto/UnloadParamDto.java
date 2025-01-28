package ru.hofftech.logisticservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Параметры загрузки данных по машинам из файла переданным в параметре -in-filename и выгрузка результатов")
public class UnloadParamDto {

    @Schema(description = "Имя входного файла")
    private String inFilename;

    @Schema(description = "Имя выходного файла")
    private String outFilename;

    @Schema(description = "Добавить колонку с количеством")
    private boolean withCount;
}
