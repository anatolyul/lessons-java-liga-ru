package ru.hofftech.console.packages.model.converter;

import org.junit.jupiter.api.Test;
import ru.hofftech.console.packages.model.ConsoleCommand;

import static org.assertj.core.api.Assertions.assertThat;

class ConsoleCommandConverterTest {

    @Test
    void convertStringToEnumImportFileCommand() {
        ConsoleCommandConverter consoleCommandConverter = new ConsoleCommandConverter();
        String importFileCommand = "import boxes.txt";

        ConsoleCommand consoleCommand = consoleCommandConverter.convertStringToEnum(importFileCommand);

        assertThat(consoleCommand).isEqualTo(ConsoleCommand.IMPORT_FILE);
    }
}