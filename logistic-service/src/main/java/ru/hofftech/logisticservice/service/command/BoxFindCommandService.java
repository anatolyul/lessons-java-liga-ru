package ru.hofftech.logisticservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.model.Command;
import ru.hofftech.logisticservice.model.enums.Argument;
import ru.hofftech.logisticservice.service.BoxService;
import ru.hofftech.logisticservice.service.CommandExecutor;

import java.util.Map;
import java.util.Optional;

/**
 * Сервис для выполнения команды поиска коробки.
 */
@Service
@RequiredArgsConstructor
public class BoxFindCommandService implements CommandExecutor {
    private final BoxService boxService;

    /**
     * Выполняет команду поиска коробки.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(Command command) {
        Map<Argument, String> arguments = command.arguments();

        Optional<BoxDto> result = boxService.findByName(arguments.get(Argument.ID));
        if (result.isEmpty()) {
            result = boxService.findByName(arguments.get(Argument.NAME));
        }

        return result.toString();
    }
}
