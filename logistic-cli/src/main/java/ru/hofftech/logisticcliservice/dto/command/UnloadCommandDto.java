package ru.hofftech.logisticcliservice.dto.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UnloadCommandDto implements BaseCommandDto {

    private String clientName;
    private LocalDate date;
    private String inFilename;
    private String outFilename;
    private boolean withCount;
}
