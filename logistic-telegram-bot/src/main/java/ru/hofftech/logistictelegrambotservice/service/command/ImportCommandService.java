package ru.hofftech.logistictelegrambotservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logistictelegrambotservice.dto.BoxDto;
import ru.hofftech.logistictelegrambotservice.dto.CommandDto;
import ru.hofftech.logistictelegrambotservice.dto.ImportParamDto;
import ru.hofftech.logistictelegrambotservice.enums.Argument;
import ru.hofftech.logistictelegrambotservice.service.CommandExecutor;
import ru.hofftech.logistictelegrambotservice.service.LogisticService;

import java.util.List;
import java.util.Map;


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
    public String execute(CommandDto command) {
        Map<Argument, String> arguments = command.getArguments();
        ImportParamDto importParamDto =
                ImportParamDto.builder()
                        .filename(arguments.get(Argument.IMPORT_FILENAME))
                        .build();
        List<BoxDto> boxes = logisticService.importBoxes(importParamDto);

        return "Посылки загружены в БД кол-во " + boxes.size();
    }
}
