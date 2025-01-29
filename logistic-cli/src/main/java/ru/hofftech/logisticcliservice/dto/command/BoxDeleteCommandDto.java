package ru.hofftech.logisticcliservice.dto.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BoxDeleteCommandDto implements BaseCommandDto {

    private String boxName;
}
