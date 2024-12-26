package ru.hofftech.console.packages.model.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;

import static org.assertj.core.api.Assertions.assertThat;

class ConsoleCommandConverterTest {
    private ConsoleCommandConverter consoleCommandConverter;

    @BeforeEach
    void setUp() {
        consoleCommandConverter = new ConsoleCommandConverter();
    }

    @Test
    void convertStringToEnum_givenImportFileTxtCommand_shouldReturnConsoleCommand() {
        String importFileCommand = "import boxes.txt";
        ConsoleCommand consoleCommand = consoleCommandConverter.convertStringToEnum(importFileCommand);
        assertThat(consoleCommand).isEqualTo(ConsoleCommand.IMPORT_FILE_TXT);
    }

    @Test
    void convertStringToEnum_givenImportFileJsonCommand_shouldReturnConsoleCommand() {
        String importFileCommand = "import trucks.json";
        ConsoleCommand consoleCommand = consoleCommandConverter.convertStringToEnum(importFileCommand);
        assertThat(consoleCommand).isEqualTo(ConsoleCommand.IMPORT_FILE_JSON);
    }
}