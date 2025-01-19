package ru.hofftech.console.packages.model.enums;

import lombok.Getter;

/**
 * Перечисление аргументов команд.
 */
@Getter
public enum Argument {
    /**
     * Аргумент для имени.
     */
    NAME("--name"),

    /**
     * Аргумент для формы.
     */
    FORM("--form"),

    /**
     * Аргумент для символа.
     */
    SYMBOL("--symbol"),

    /**
     * Аргумент для идентификатора.
     */
    ID("--id"),

    /**
     * Аргумент для текста с именами посылок.
     */
    PARCELS_TEXT("--parcels-text"),

    /**
     * Аргумент для файла с именами посылок.
     */
    PARCELS_FILE("--parcels-file"),

    /**
     * Аргумент для грузовиков.
     */
    TRUCKS("--trucks"),

    /**
     * Аргумент для лимита.
     */
    LIMIT("--limit"),

    /**
     * Аргумент для типа.
     */
    TYPE("--type"),

    /**
     * Аргумент для имени входного файла.
     */
    IN_FILENAME("--in-filename"),

    /**
     * Аргумент для имени выходного файла.
     */
    OUT_FILENAME("--out-filename"),

    /**
     * Аргумент для подсчета.
     */
    WITHCOUNT("--withcount"),

    IMPORT_FILENAME("--import-filename"),

    USER("--user"),

    PERIOD_FROM("--period-from"),

    PERIOD_TO("--period-to"),

    /**
     * Неизвестный аргумент.
     */
    UNKNOWN("");

    private final String code;

    /**
     * Конструктор для аргумента.
     *
     * @param code строковое представление аргумента
     */
    Argument(String code) {
        this.code = code;
    }
}
