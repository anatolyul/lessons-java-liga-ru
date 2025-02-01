package ru.hofftech.logisticcliservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticcliservice.dto.BoxDto;
import ru.hofftech.logisticcliservice.dto.command.BoxFindCommandDto;
import ru.hofftech.logisticcliservice.service.CommandExecutor;
import ru.hofftech.logisticcliservice.service.LogisticService;

/**
 * Сервис для выполнения команды поиска коробки.
 */
@Service
@RequiredArgsConstructor
public class BoxFindCommandService implements CommandExecutor<BoxFindCommandDto> {

    private final LogisticService logisticService;

    /**
     * Выполняет команду поиска коробки.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(BoxFindCommandDto command) {

        try {
            BoxDto result = logisticService.findBoxByName(command.getBoxName());
            return result != null ? result.toString() : "Посылка не найдена!";
        }
        catch (Exception e) {
            return "Ошибка обработки запроса, сервис логистики не доступен";
        }
    }
}
