package ru.hofftech.logistictelegrambotservice.enums;

import lombok.Getter;

/**
 * Перечисление консольных команд.
 */
@Getter
public enum ConsoleCommand {
    /**
     * Команда для завершения работы.
     */
    EXIT("exit"),

    /**
     * Команда для импорта файла TXT.
     */
    IMPORT_FILE_TXT("import"),

    /**
     * Команда для импорта файла JSON.
     */
    IMPORT_FILE_JSON("import"),

    /**
     * Команда для создания коробки.
     */
    BOX_CREATE("create"),

    /**
     * Команда для редактирования коробки.
     */
    BOX_EDIT("edit"),

    /**
     * Команда для поиска коробки.
     */
    BOX_FIND("find"),

    /**
     * Команда для удаления коробки.
     */
    BOX_DELETE("delete"),

    /**
     * Команда для отображения списка коробок.
     */
    BOX_LIST("list"),

    /**
     * Команда для загрузки коробок в грузовики.
     */
    LOAD("load"),

    /**
     * Команда для разгрузки коробок из грузовиков.
     */
    UNLOAD("unload"),

    /**
     * Команда для отображения справочной информации.
     */
    HELP("help"),

    /**
     * Команда для биллинга.
     */
    BILLING("billing"),

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

    /**
     * Преобразует строковое представление консольной команды в перечисление ConsoleCommand.
     *
     * @param consoleCommand строковое представление консольной команды
     * @return перечисление ConsoleCommand, соответствующее строковому представлению
     */
    public static ConsoleCommand convertStringToEnum(String consoleCommand) {
        for (ConsoleCommand command : ConsoleCommand.values()) {
            if (command.getCode().equals(consoleCommand)) {
                return command;
            }
        }
        return ConsoleCommand.UNKNOWN;
    }
}
