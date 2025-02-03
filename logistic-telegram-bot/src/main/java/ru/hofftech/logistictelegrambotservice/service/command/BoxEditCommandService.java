package ru.hofftech.logistictelegrambotservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logistictelegrambotservice.dto.BoxForUpdateDto;
import ru.hofftech.logistictelegrambotservice.dto.CommandDto;
import ru.hofftech.logistictelegrambotservice.enums.Argument;
import ru.hofftech.logistictelegrambotservice.service.CommandExecutor;
import ru.hofftech.logistictelegrambotservice.service.LogisticService;

import java.util.Map;

/**
 * Сервис для выполнения команды редактирования коробки.
 */
@Service
@RequiredArgsConstructor
public class BoxEditCommandService implements CommandExecutor {

    private final LogisticService logisticService;

    /**
     * Выполняет команду редактирования коробки.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(CommandDto command) {
        Map<Argument, String> arguments = command.getArguments();
        BoxForUpdateDto boxUpd = new BoxForUpdateDto();
        boxUpd.setOldName(arguments.get(Argument.ID));
        boxUpd.setName(arguments.get(Argument.NAME));
        boxUpd.setForm(arguments.get(Argument.FORM));
        boxUpd.setSymbol(arguments.get(Argument.SYMBOL));
        return logisticService.updateBox(boxUpd).toString();
    }
}
