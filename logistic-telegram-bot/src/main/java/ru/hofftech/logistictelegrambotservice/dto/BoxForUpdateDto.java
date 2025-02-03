package ru.hofftech.logistictelegrambotservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoxForUpdateDto extends BoxDto {
    private String oldName;
}
