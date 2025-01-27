package ru.hofftech.logisticservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.model.Command;
import ru.hofftech.logisticservice.model.enums.Argument;
import ru.hofftech.logisticservice.service.BoxService;
import ru.hofftech.logisticservice.service.CommandExecutor;

import java.util.Map;

/**
 * Сервис для выполнения команды удаления коробки.
 */
@Service
@RequiredArgsConstructor
public class BoxDeleteCommandService implements CommandExecutor {
    private final BoxService boxService;

    /**
     * Выполняет команду удаления коробки.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(Command command) {
        Map<Argument, String> arguments = command.arguments();
        boolean result = boxService.delete(arguments.get(Argument.NAME));
        if (result) {
            return "Посылка удалена";
        } else {
            return "Посылка не найдена, удаление запрещено!";
        }
    }
}
