package ru.hofftech.logistictelegrambotservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logistictelegrambotservice.dto.BoxDto;
import ru.hofftech.logistictelegrambotservice.dto.CommandDto;
import ru.hofftech.logistictelegrambotservice.enums.Argument;
import ru.hofftech.logistictelegrambotservice.service.CommandExecutor;
import ru.hofftech.logistictelegrambotservice.service.LogisticService;

import java.util.Map;

/**
 * Сервис для выполнения команды создания коробки.
 */
@Service
@RequiredArgsConstructor
public class BoxCreateCommandService implements CommandExecutor {

    private final LogisticService logisticService;

    /**
     * Выполняет команду создания коробки.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(CommandDto command) {
        Map<Argument, String> arguments = command.getArguments();
        BoxDto boxNew = new BoxDto();
        boxNew.setName(arguments.get(Argument.NAME));
        boxNew.setForm(arguments.get(Argument.FORM));
        boxNew.setSymbol(arguments.get(Argument.SYMBOL));

        return logisticService.createBox(boxNew).toString();
    }
}
