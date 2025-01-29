package ru.hofftech.logisticcliservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticcliservice.dto.command.BaseCommandDto;
import ru.hofftech.logisticcliservice.dto.command.BoxEditCommandDto;
import ru.hofftech.logisticcliservice.service.CommandExecutor;
import ru.hofftech.logisticcliservice.service.LogisticService;

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
    public String execute(BaseCommandDto command) {
        return logisticService.updateBox((BoxEditCommandDto) command).toString();
    }
}
