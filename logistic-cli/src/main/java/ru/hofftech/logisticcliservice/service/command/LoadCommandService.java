package ru.hofftech.logisticcliservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticcliservice.dto.TruckDto;
import ru.hofftech.logisticcliservice.dto.command.BaseCommandDto;
import ru.hofftech.logisticcliservice.dto.command.LoadCommandDto;
import ru.hofftech.logisticcliservice.service.CommandExecutor;
import ru.hofftech.logisticcliservice.service.LogisticService;

import java.util.stream.Collectors;

/**
 * Сервис для выполнения команды загрузки коробок в грузовики.
 */
@Service
@RequiredArgsConstructor
public class LoadCommandService implements CommandExecutor {

    private final LogisticService logisticService;

    /**
     * Выполняет команду загрузки коробок в грузовики.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(BaseCommandDto command) {
        return """
                
                Результаты распределения груза:
                """ +
                logisticService.loadBoxes((LoadCommandDto) command).stream()
                        .map(TruckDto::toString)
                        .collect(Collectors.joining("\n"));
    }
}
