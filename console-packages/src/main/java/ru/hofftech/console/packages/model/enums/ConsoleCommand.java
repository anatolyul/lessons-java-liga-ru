package ru.hofftech.console.packages.model.enums;

import lombok.Getter;

@Getter
public enum ConsoleCommand {
    EXIT("exit"),
    FIRST_ALGORITHM("1"),
    SECOND_ALGORITHM("2"),
    THIRD_ALGORITHM("3"),
    IMPORT_FILE_TXT("import"),
    IMPORT_FILE_JSON("import"),
    LIMIT("limit"),
    UNKNOWN("");

    private final String consoleCommand;

    ConsoleCommand(String consoleCommand) {
        this.consoleCommand = consoleCommand;
    }
}
