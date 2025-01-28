package ru.hofftech.logisticservice.model.enums;

import lombok.Getter;

/**
 * Перечисление консольных команд.
 */
@Getter
public enum ConsoleCommand {

    /**
     * Команда для импорта файла TXT.
     */
    IMPORT_FILE_TXT("import (.+\\.txt)\""),

    /**
     * Команда для импорта файла JSON.
     */
    IMPORT_FILE_JSON("import (.+\\.json)\""),

    /**
     * Команда для загрузки коробок в грузовики.
     */
    LOAD("load"),

    /**
     * Команда для разгрузки коробок из грузовиков.
     */
    UNLOAD("unload"),

    /**
     * Неизвестная команда.
     */
    UNKNOWN("");

    private final String code;

    /**
     * Конструктор для консольной команды.
     *
     * @param code строковое представление консольной команды
     */
    ConsoleCommand(String code) {
        this.code = code;
    }

    public static ConsoleCommand fromExtension(String filename) {
        if (filename.endsWith(".json")) {
            return IMPORT_FILE_JSON;
        } else if (filename.endsWith(".txt")) {
            return IMPORT_FILE_TXT;
        }
        throw new IllegalArgumentException("Unsupported file extension: " + filename);
    }
}
