package ru.hofftech.console.packages.model.enums;

import lombok.Getter;

@Getter
public enum TypeAlgorithm {
    ONE_TO_ONE("one2one"),
    MAXIMUM_LOAD("max"),
    UNIFORM_LOAD("uniform");

    private final String code;

    TypeAlgorithm(String code) { this.code = code; }
}
