package ru.hofftech.logisticcliservice.dto.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UnloadCommandDto implements BaseCommandDto {

    private String inFilename;
    private String outFilename;
    private boolean withCount;
}
