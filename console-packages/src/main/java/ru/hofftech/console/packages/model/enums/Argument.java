package ru.hofftech.console.packages.model.enums;

import lombok.Getter;

@Getter
public enum Argument {
    NAME("-name"),
    FORM("-form"),
    SYMBOL("-symbol"),
    ID("-id"),
    PARCELS_TEXT("-parcels-text"),
    PARCELS_FILE("-parcels-file"),
    TRUCKS("-trucks"),
    LIMIT("-limit"),
    TYPE("-type"),
    IN_FILENAME("-in-filename"),
    OUT_FILENAME("-out-filename"),
    WITHCOUNT("-withcount"),
    UNKNOWN("");

    private final String argument;

    Argument(String argument) {
        this.argument = argument;
    }
}
