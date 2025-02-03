package ru.hofftech.logisticservice.model.enums;

import lombok.Getter;

/**
 * Перечисление типа загрузки данных.
 */
@Getter
public enum TypeLoadData {

    /**
     * Загрузка из файла TXT.
     */
    IMPORT_FILE_TXT("import (.+\\.txt)\""),

    /**
     * Загрузка из файла JSON.
     */
    IMPORT_FILE_JSON("import (.+\\.json)\""),

    /**
     * Загрузка коробок в грузовики.
     */
    LOAD("load");

    private final String code;

    /**
     * Конструктор для типа загрузки данных.
     *
     * @param code строковое представление типа загрузки данных
     */
    TypeLoadData(String code) {
        this.code = code;
    }

    public static TypeLoadData fromExtension(String filename) {
        if (filename.endsWith(".json")) {
            return IMPORT_FILE_JSON;
        } else if (filename.endsWith(".txt")) {
            return IMPORT_FILE_TXT;
        }
        throw new IllegalArgumentException("Unsupported file extension: " + filename);
    }
}
