package ru.hofftech.consolepackages.service;

import ru.hofftech.consolepackages.model.Command;

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
