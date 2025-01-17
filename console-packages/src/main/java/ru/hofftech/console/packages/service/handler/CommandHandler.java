package ru.hofftech.console.packages.service.handler;

import lombok.RequiredArgsConstructor;
import ru.hofftech.console.packages.model.Command;
import ru.hofftech.console.packages.service.CommandExecutor;
import ru.hofftech.console.packages.service.converter.CommandArgConverterService;
import ru.hofftech.console.packages.service.factory.CommandExecutorFactory;

/**
 * Обработчик команд для управления грузовиками и коробками.
 */
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
        CommandExecutor commandExecutor = commandExecutorFactory.create(commandArgs.getCommand());

        return commandExecutor.execute(commandArgs);
    }
}
