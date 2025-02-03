package ru.hofftech.logisticcliservice.dto.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BoxCreateCommandDto implements BaseCommandDto {

    private String name;
    private String symbol;
    private String form;
}
