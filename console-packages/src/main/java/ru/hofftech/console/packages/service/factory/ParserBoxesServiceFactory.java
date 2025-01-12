package ru.hofftech.console.packages.service.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.service.ParserBoxesService;
import ru.hofftech.console.packages.service.impl.ParserBoxesServiceCsv;
import ru.hofftech.console.packages.service.impl.ParserBoxesServiceJson;
import ru.hofftech.console.packages.service.impl.ParserBoxesServiceTxt;

@RequiredArgsConstructor
public class ParserBoxesServiceFactory {
    public ParserBoxesService create(BoxRepository boxRepository, ConsoleCommand command) {
        return switch (command) {
            case ConsoleCommand.IMPORT_FILE_JSON -> new ParserBoxesServiceJson(boxRepository, new ObjectMapper());
            case ConsoleCommand.IMPORT_FILE_TXT -> new ParserBoxesServiceTxt(boxRepository);
            case ConsoleCommand.LOAD -> new ParserBoxesServiceCsv(boxRepository);
            default -> throw new IllegalStateException("Unexpected value: " + command);
        };
    }
}
