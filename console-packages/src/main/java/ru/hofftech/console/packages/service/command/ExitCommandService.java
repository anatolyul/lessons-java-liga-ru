package ru.hofftech.console.packages.service.command;

import org.springframework.stereotype.Service;
import ru.hofftech.console.packages.model.Command;
import ru.hofftech.console.packages.service.CommandExecutor;

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
