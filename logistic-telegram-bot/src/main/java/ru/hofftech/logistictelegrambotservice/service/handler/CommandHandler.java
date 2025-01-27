package ru.hofftech.logistictelegrambotservice.service.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logistictelegrambotservice.converter.CommandArgConverter;
import ru.hofftech.logistictelegrambotservice.service.LogisticService;

@Service
@RequiredArgsConstructor
public class CommandHandler {

    private final LogisticService logisticService;
    private final CommandArgConverter commandArgConverter;

    public String handle(String command) {
        return logisticService.executeCommand(
                        commandArgConverter.parseCommandArgs(command))
                .getResultCommandExecuted();
    }
}
