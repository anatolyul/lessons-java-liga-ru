package ru.hofftech.logisticservice.service.command;

import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.model.Command;
import ru.hofftech.logisticservice.service.CommandExecutor;

/**
 * Сервис для выполнения команды завершения работы приложения.
 */
@Service
public class ExitCommandService implements CommandExecutor {

    /**
     * Выполняет команду завершения работы приложения.
     *
     * @param command команда для выполнения
     * @return null, так как приложение завершает работу
     */
    @Override
    public String execute(Command command) {
        System.exit(0);
        return null;
    }
}
