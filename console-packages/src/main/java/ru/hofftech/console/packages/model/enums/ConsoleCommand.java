package ru.hofftech.console.packages.model.enums;

import lombok.Getter;

@Getter
public enum ConsoleCommand {
    EXIT("exit"),
    IMPORT_FILE_TXT("import (.+\\.txt)"),
    IMPORT_FILE_JSON("import (.+\\.json)"),
    BOX_CREATE("create"),
    BOX_EDIT("edit"),
    BOX_FIND("find"),
    BOX_DELETE("delete"),
    BOX_LIST("list"),
    LOAD("load"),
    UNLOAD("unload"),
    UNKNOWN("");

    private final String consoleCommand;

    ConsoleCommand(String consoleCommand) {
        this.consoleCommand = consoleCommand;
    }
}
