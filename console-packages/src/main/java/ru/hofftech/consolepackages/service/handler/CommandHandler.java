package ru.hofftech.consolepackages.service.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.consolepackages.model.Command;
import ru.hofftech.consolepackages.service.CommandExecutor;
import ru.hofftech.consolepackages.service.converter.CommandArgConverterService;
import ru.hofftech.consolepackages.service.factory.CommandExecutorFactory;

/**
 * Обработчик команд для управления грузовиками и коробками.
 */
@Service
@RequiredArgsConstructor
public class CommandHandler {
    private final CommandArgConverterService commandArgConverterService;
    private final CommandExecutorFactory commandExecutorFactory;

    /**
     * Обрабатывает команду и выполняет соответствующие действия.
     *
     * @param commandString строка команды
     * @return результат выполнения команды
     */
    public String handle(String commandString) {
        Command commandArgs = commandArgConverterService.parseCommandArgs(commandString);
        CommandExecutor commandExecutor = commandExecutorFactory.create(commandArgs.consoleCommand());

        return commandExecutor.execute(commandArgs);
    }

    /**
     * Обрабатывает команду и выполняет соответствующие действия.
     *
     * @param commandArgs объект команды
     * @return результат выполнения команды
     */
    public String handle(Command commandArgs) {
        CommandExecutor commandExecutor = commandExecutorFactory.create(commandArgs.consoleCommand());

        return commandExecutor.execute(commandArgs);
    }
}
