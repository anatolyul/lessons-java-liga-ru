package ru.hofftech.logisticcliservice.service;

import ru.hofftech.logisticcliservice.dto.command.BaseCommandDto;

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
    String execute(BaseCommandDto command);
}
