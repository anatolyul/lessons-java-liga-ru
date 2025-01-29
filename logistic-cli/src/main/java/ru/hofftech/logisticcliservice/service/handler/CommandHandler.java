package ru.hofftech.logisticcliservice.service.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticcliservice.dto.command.BaseCommandDto;
import ru.hofftech.logisticcliservice.service.CommandExecutor;
import ru.hofftech.logisticcliservice.service.factory.CommandExecutorFactory;

@Service
@RequiredArgsConstructor
public class CommandHandler {

    private final CommandExecutorFactory commandExecutorFactory;

    public String handle(BaseCommandDto baseCommandDto) {
        CommandExecutor commandExecutor = commandExecutorFactory.create(baseCommandDto);

        return commandExecutor.execute(baseCommandDto);
    }
}
