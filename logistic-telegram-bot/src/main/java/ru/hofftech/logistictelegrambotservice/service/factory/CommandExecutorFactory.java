package ru.hofftech.logistictelegrambotservice.service.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logistictelegrambotservice.enums.ConsoleCommand;
import ru.hofftech.logistictelegrambotservice.service.CommandExecutor;

import java.util.Map;

/**
 * Фабрика для создания экземпляров CommandExecutor на основе переданной консольной команды.
 */
@Service
@RequiredArgsConstructor
public class CommandExecutorFactory {
    private final Map<ConsoleCommand, CommandExecutor> commandExecutorMap;

    /**
     * Создает экземпляр CommandExecutor на основе переданной консольной команды.
     *
     * @param command консольная команда
     * @return экземпляр CommandExecutor, соответствующий переданной команде
     */
    public CommandExecutor create(ConsoleCommand command) {
        return commandExecutorMap.get(command);
    }
}
