package ru.hofftech.console.packages.service.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;
import ru.hofftech.console.packages.service.ParserBoxesService;
import ru.hofftech.console.packages.service.impl.ParserBoxesServiceJson;
import ru.hofftech.console.packages.service.impl.ParserBoxesServiceTxt;

public class ParserBoxesServiceFactory {
    public ParserBoxesService create(ConsoleCommand command) {
        return switch (command) {
            case ConsoleCommand.IMPORT_FILE_JSON -> new ParserBoxesServiceJson(new ObjectMapper());
            case ConsoleCommand.IMPORT_FILE_TXT -> new ParserBoxesServiceTxt();
            default -> throw new IllegalStateException("Unexpected value: " + command);
        };
    }
}
