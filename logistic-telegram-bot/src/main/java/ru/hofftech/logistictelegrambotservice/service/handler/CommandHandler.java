package ru.hofftech.logistictelegrambotservice.service.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logistictelegrambotservice.converter.CommandArgConverter;
import ru.hofftech.logistictelegrambotservice.dto.CommandDto;
import ru.hofftech.logistictelegrambotservice.service.CommandExecutor;
import ru.hofftech.logistictelegrambotservice.service.factory.CommandExecutorFactory;

@Service
@RequiredArgsConstructor
public class CommandHandler {

    private final CommandArgConverter commandArgConverter;
    private final CommandExecutorFactory commandExecutorFactory;

    public String handle(String command) {
        CommandDto commandArgs = commandArgConverter.parseCommandArgs(command);
        CommandExecutor commandExecutor = commandExecutorFactory.create(commandArgs.getConsoleCommand());

        return commandExecutor.execute(commandArgs);
    }
}
