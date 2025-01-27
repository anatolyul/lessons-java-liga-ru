package ru.hofftech.logisticservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.model.Command;
import ru.hofftech.logisticservice.model.enums.Argument;
import ru.hofftech.logisticservice.service.BoxService;
import ru.hofftech.logisticservice.service.CommandExecutor;

import java.util.Map;

/**
 * Сервис для выполнения команды редактирования коробки.
 */
@Service
@RequiredArgsConstructor
public class BoxEditCommandService implements CommandExecutor {
    private final BoxService boxService;

    /**
     * Выполняет команду редактирования коробки.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(Command command) {
        Map<Argument, String> arguments = command.arguments();
        BoxDto boxUpd = new BoxDto();
        boxUpd.setName(arguments.get(Argument.NAME));
        boxUpd.setForm(arguments.get(Argument.FORM));
        boxUpd.setSymbol(arguments.get(Argument.SYMBOL));
        return boxService.update(arguments.get(Argument.ID), boxUpd).toString();
    }
}
