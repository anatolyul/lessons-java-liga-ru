package ru.hofftech.logisticcliservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticcliservice.dto.command.BoxCreateCommandDto;
import ru.hofftech.logisticcliservice.service.CommandExecutor;
import ru.hofftech.logisticcliservice.service.LogisticService;

/**
 * Сервис для выполнения команды создания коробки.
 */
@Service
@RequiredArgsConstructor
public class BoxCreateCommandService implements CommandExecutor<BoxCreateCommandDto> {

    private final LogisticService logisticService;

    /**
     * Выполняет команду создания коробки.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(BoxCreateCommandDto command) {
        return logisticService.createBox(command).toString();
    }
}
