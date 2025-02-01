package ru.hofftech.logisticcliservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticcliservice.dto.command.BaseCommandDto;
import ru.hofftech.logisticcliservice.dto.command.ImportCommandDto;
import ru.hofftech.logisticcliservice.service.CommandExecutor;
import ru.hofftech.logisticcliservice.service.LogisticService;

/**
 * Сервис для выполнения команды импорта коробок.
 */
@Service
@RequiredArgsConstructor
public class ImportCommandService implements CommandExecutor {

    private final LogisticService logisticService;

    /**
     * Выполняет команду импорта коробок.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(BaseCommandDto command) {
        return "Посылки загружены в БД кол-во " + logisticService.importBoxes((ImportCommandDto) command).size();
    }
}
