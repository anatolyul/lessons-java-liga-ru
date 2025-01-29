package ru.hofftech.logisticcliservice.dto.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.hofftech.logisticcliservice.enums.TypeAlgorithm;

@Getter
@Setter
@Builder
public class LoadCommandDto implements BaseCommandDto {

    private String parcelsText;
    private String parcelsFile;
    private String trucks;
    private TypeAlgorithm type;
    private String outFilename;
}
