package ru.hofftech.console.packages.model.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;
import ru.hofftech.console.packages.service.converter.CommandArgConverterService;

import static org.assertj.core.api.Assertions.assertThat;

class CommandArgConverterServiceTest {
    private CommandArgConverterService commandArgConverterService;

    @BeforeEach
    void setUp() {
        commandArgConverterService = new CommandArgConverterService();
    }

    @Test
    void convertStringToEnum_givenImportFileTxtCommand_shouldReturnConsoleCommand() {
        String importFileCommand = "import boxes.txt";
        ConsoleCommand consoleCommand = commandArgConverterService.convertCommandStringToEnum(importFileCommand);
        assertThat(consoleCommand).isEqualTo(ConsoleCommand.IMPORT_FILE_TXT);
    }

    @Test
    void convertStringToEnum_givenImportFileJsonCommand_shouldReturnConsoleCommand() {
        String importFileCommand = "import trucks.json";
        ConsoleCommand consoleCommand = commandArgConverterService.convertCommandStringToEnum(importFileCommand);
        assertThat(consoleCommand).isEqualTo(ConsoleCommand.IMPORT_FILE_JSON);
    }
}