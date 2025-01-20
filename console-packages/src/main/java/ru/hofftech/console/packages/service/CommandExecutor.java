package ru.hofftech.console.packages.service;

import org.springframework.stereotype.Service;
import ru.hofftech.console.packages.model.Command;

/**
 * Интерфейс для выполнения команд.
 */
@Service
public interface CommandExecutor {

    /**
     * Выполняет команду и возвращает результат выполнения.
     *
     * @param command команда для выполнения
     * @return результат выполнения команды
     */
    String execute(Command command);
}
