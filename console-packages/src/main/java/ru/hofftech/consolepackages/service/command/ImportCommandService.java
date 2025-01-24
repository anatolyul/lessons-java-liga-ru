package ru.hofftech.consolepackages.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.consolepackages.model.Box;
import ru.hofftech.consolepackages.model.Command;
import ru.hofftech.consolepackages.model.enums.Argument;
import ru.hofftech.consolepackages.service.CommandExecutor;
import ru.hofftech.consolepackages.service.converter.CommandArgConverterService;
import ru.hofftech.consolepackages.service.factory.ParserBoxesServiceFactory;

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
        List<Box> result = parserBoxesServiceFactory
                .create(command.consoleCommand(),
                        commandArgConverterService.fileToPath(
                                command.arguments().get(Argument.IMPORT_FILENAME)));
        return "Посылки загружены Кол-во " + result.size();
    }
}
