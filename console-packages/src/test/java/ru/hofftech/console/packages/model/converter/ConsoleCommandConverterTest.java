package ru.hofftech.console.packages.model.converter;

import org.junit.jupiter.api.Test;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;

import static org.assertj.core.api.Assertions.assertThat;

class ConsoleCommandConverterTest {

    @Test
    void convertStringToEnumImportFileTxtCommand() {
        ConsoleCommandConverter consoleCommandConverter = new ConsoleCommandConverter();
        String importFileCommand = "import boxes.txt";

        ConsoleCommand consoleCommand = consoleCommandConverter.convertStringToEnum(importFileCommand);

        assertThat(consoleCommand).isEqualTo(ConsoleCommand.IMPORT_FILE_TXT);
    }

    @Test
    void convertStringToEnumImportFileJsonCommand() {
        ConsoleCommandConverter consoleCommandConverter = new ConsoleCommandConverter();
        String importFileCommand = "import trucks.json";

        ConsoleCommand consoleCommand = consoleCommandConverter.convertStringToEnum(importFileCommand);

        assertThat(consoleCommand).isEqualTo(ConsoleCommand.IMPORT_FILE_JSON);
    }
}