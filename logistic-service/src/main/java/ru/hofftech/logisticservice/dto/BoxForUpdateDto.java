package ru.hofftech.logisticservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoxForUpdateDto extends BoxDto {

    @Schema(description = "Предыдущее наименование посылки")
    private String oldName;
}
