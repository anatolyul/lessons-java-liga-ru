package ru.hofftech.consolepackages.model.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.model.enums.ConsoleCommand;
import ru.hofftech.consolepackages.service.converter.CommandArgConverterService;

import static org.assertj.core.api.Assertions.assertThat;

class CommandArgConverterServiceTest {
    private CommandArgConverterService commandArgConverterService;

    @BeforeEach
    void setUp() {
        commandArgConverterService = new CommandArgConverterService();
    }

    @Test
    void convertStringToEnum_givenImportFileTxtCommand_shouldReturnConsoleCommand() {
        String importFileCommand = "import --import-filename \"boxes.txt\"";
        ConsoleCommand consoleCommand = commandArgConverterService.convertCommandStringToEnum(importFileCommand);
        assertThat(consoleCommand).isEqualTo(ConsoleCommand.IMPORT_FILE_TXT);
    }

    @Test
    void convertStringToEnum_givenImportFileJsonCommand_shouldReturnConsoleCommand() {
        String importFileCommand = "import --import-filename \"trucks.json\"";
        ConsoleCommand consoleCommand = commandArgConverterService.convertCommandStringToEnum(importFileCommand);
        assertThat(consoleCommand).isEqualTo(ConsoleCommand.IMPORT_FILE_JSON);
    }
}