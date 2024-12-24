package ru.hofftech.console.packages.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;
import ru.hofftech.console.packages.util.impl.ParserBoxesJson;
import ru.hofftech.console.packages.util.impl.ParserBoxesTxt;

public class ParserBoxesFactory {
    public static ParserBoxes createParserBoxes(ConsoleCommand command) {
        return switch (command) {
            case IMPORT_FILE_TXT -> new ParserBoxesTxt();
            case IMPORT_FILE_JSON -> new ParserBoxesJson(new ObjectMapper());
            default -> new ParserBoxesTxt();
        };
    }
}
