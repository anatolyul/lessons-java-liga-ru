package ru.hofftech.logisticcliservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticcliservice.dto.command.BoxDeleteCommandDto;
import ru.hofftech.logisticcliservice.service.CommandExecutor;
import ru.hofftech.logisticcliservice.service.LogisticService;

/**
 * Сервис для выполнения команды удаления коробки.
 */
@Service
@RequiredArgsConstructor
public class BoxDeleteCommandService implements CommandExecutor<BoxDeleteCommandDto> {

    private final LogisticService logisticService;

    /**
     * Выполняет команду удаления коробки.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(BoxDeleteCommandDto command) {
        boolean result = logisticService.deleteBoxByName(command.getBoxName());
        if (result) {
            return "Посылка удалена";
        } else {
            return "Посылка не найдена, удаление запрещено!";
        }
    }
}
