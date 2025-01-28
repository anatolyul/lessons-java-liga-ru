package ru.hofftech.logistictelegrambotservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logistictelegrambotservice.dto.BoxDto;
import ru.hofftech.logistictelegrambotservice.dto.CommandDto;
import ru.hofftech.logistictelegrambotservice.enums.Argument;
import ru.hofftech.logistictelegrambotservice.service.CommandExecutor;
import ru.hofftech.logistictelegrambotservice.service.LogisticService;

import java.util.Map;
import java.util.Optional;

/**
 * Сервис для выполнения команды поиска коробки.
 */
@Service
@RequiredArgsConstructor
public class BoxFindCommandService implements CommandExecutor {

    private final LogisticService logisticService;

    /**
     * Выполняет команду поиска коробки.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(CommandDto command) {
        Map<Argument, String> arguments = command.getArguments();

        try {
            BoxDto result = Optional.ofNullable(arguments.get(Argument.ID))
                    .map(logisticService::findBoxByName)
                    .orElseGet(() -> Optional.ofNullable(arguments.get(Argument.NAME))
                            .map(logisticService::findBoxByName)
                            .orElse(null));
            return result != null ? result.toString() : "Посылка не найдена!";
        }
        catch (Exception e) {
            return "Ошибка обработки запроса, сервис логистики не доступен";
        }
    }
}
