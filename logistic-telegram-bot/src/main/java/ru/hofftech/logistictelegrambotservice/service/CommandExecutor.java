package ru.hofftech.logistictelegrambotservice.service;

import ru.hofftech.logistictelegrambotservice.dto.CommandDto;

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
    String execute(CommandDto command);
}
