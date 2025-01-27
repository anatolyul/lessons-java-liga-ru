package ru.hofftech.logisticservice.service.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.model.Command;
import ru.hofftech.logisticservice.service.CommandExecutor;
import ru.hofftech.logisticservice.service.factory.CommandExecutorFactory;

/**
 * Обработчик команд для управления грузовиками и коробками.
 */
@Service
@RequiredArgsConstructor
public class CommandHandler {
    private final CommandExecutorFactory commandExecutorFactory;

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
