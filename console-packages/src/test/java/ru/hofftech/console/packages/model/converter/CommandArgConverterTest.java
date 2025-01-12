package ru.hofftech.console.packages.model.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;

import static org.assertj.core.api.Assertions.assertThat;

class CommandArgConverterTest {
    private CommandArgConverter commandArgConverter;

    @BeforeEach
    void setUp() {
        commandArgConverter = new CommandArgConverter();
    }

    @Test
    void convertStringToEnum_givenImportFileTxtCommand_shouldReturnConsoleCommand() {
        String importFileCommand = "import boxes.txt";
        ConsoleCommand consoleCommand = commandArgConverter.convertCommandStringToEnum(importFileCommand);
        assertThat(consoleCommand).isEqualTo(ConsoleCommand.IMPORT_FILE_TXT);
    }

    @Test
    void convertStringToEnum_givenImportFileJsonCommand_shouldReturnConsoleCommand() {
        String importFileCommand = "import trucks.json";
        ConsoleCommand consoleCommand = commandArgConverter.convertCommandStringToEnum(importFileCommand);
        assertThat(consoleCommand).isEqualTo(ConsoleCommand.IMPORT_FILE_JSON);
    }
}