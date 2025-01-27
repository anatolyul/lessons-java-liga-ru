package ru.hofftech.logisticservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.model.Command;
import ru.hofftech.logisticservice.model.enums.Argument;
import ru.hofftech.logisticservice.service.CommandExecutor;
import ru.hofftech.logisticservice.service.converter.CommandArgConverterService;
import ru.hofftech.logisticservice.service.factory.ParserBoxesServiceFactory;

import java.util.List;

/**
 * Сервис для выполнения команды импорта коробок.
 */
@Service
@RequiredArgsConstructor
public class ImportCommandService implements CommandExecutor {
    private final ParserBoxesServiceFactory parserBoxesServiceFactory;
    private final CommandArgConverterService commandArgConverterService;

    /**
     * Выполняет команду импорта коробок.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(Command command) {
        List<BoxDto> result = parserBoxesServiceFactory
                .create(command.consoleCommand(),
                        commandArgConverterService.fileToPath(
                                command.arguments().get(Argument.IMPORT_FILENAME)));
        return "Посылки загружены Кол-во " + result.size();
    }
}
