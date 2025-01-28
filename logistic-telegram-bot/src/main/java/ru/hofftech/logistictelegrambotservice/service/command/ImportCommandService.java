package ru.hofftech.logistictelegrambotservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logistictelegrambotservice.dto.CommandDto;
import ru.hofftech.logistictelegrambotservice.service.CommandExecutor;
import ru.hofftech.logistictelegrambotservice.service.LogisticService;


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
//        List<BoxDto> result = parserBoxesServiceFactory
//                .create(command.consoleCommand(),
//                        commandArgConverterService.fileToPath(
//                                command.arguments().get(Argument.IMPORT_FILENAME)));
//        return "Посылки загружены Кол-во " + result.size();
        return "";
    }
}
