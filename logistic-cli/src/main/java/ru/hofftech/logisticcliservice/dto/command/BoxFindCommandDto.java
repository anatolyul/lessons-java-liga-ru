package ru.hofftech.logisticcliservice.dto.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BoxFindCommandDto implements BaseCommandDto {

    private String boxId;
    private String boxName;
}
