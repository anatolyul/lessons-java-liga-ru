package ru.hofftech.console.packages.model;

import lombok.Getter;

@Getter
public enum ConsoleCommand {
    EXIT("exit"),
    FIRST_ALGORITHM("1"),
    SECOND_ALGORITHM("2"),
    IMPORT_FILE("import"),
    UNKNOWN("");

    private final String consoleCommand;

    ConsoleCommand(String consoleCommand) {
        this.consoleCommand = consoleCommand;
    }
}
