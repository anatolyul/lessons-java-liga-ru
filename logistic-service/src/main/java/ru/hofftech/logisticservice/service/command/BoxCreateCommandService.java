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
 * Сервис для выполнения команды создания коробки.
 */
@Service
@RequiredArgsConstructor
public class BoxCreateCommandService implements CommandExecutor {
    private final BoxService boxService;

    /**
     * Выполняет команду создания коробки.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(Command command) {
        Map<Argument, String> arguments = command.arguments();
        BoxDto boxNew = new BoxDto();
        boxNew.setName(arguments.get(Argument.NAME));
        boxNew.setForm(arguments.get(Argument.FORM));
        boxNew.setSymbol(arguments.get(Argument.SYMBOL));

        return boxService.create(boxNew).toString();
    }
}
