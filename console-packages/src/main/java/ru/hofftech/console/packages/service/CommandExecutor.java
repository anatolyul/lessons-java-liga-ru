package ru.hofftech.console.packages.service;

import ru.hofftech.console.packages.model.Command;

/**
 * Интерфейс для выполнения команд.
 */

public interface CommandExecutor {

    /**
     * Выполняет команду и возвращает результат выполнения.
     *
     * @param command команда для выполнения
     * @return результат выполнения команды
     */
    String execute(Command command);
}
