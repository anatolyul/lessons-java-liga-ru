package ru.hofftech.logistictelegrambotservice.enums;

import lombok.Getter;

@Getter
public enum TypeOrderProcess {
    LOAD("Погрузка"),
    UNLOAD("Разгрузка");

    private final String code;

    TypeOrderProcess(String code) {
        this.code = code;
    }
}
