package ru.hofftech.console.packages.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Command;
import ru.hofftech.console.packages.model.enums.Argument;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.service.CommandExecutor;
import ru.hofftech.console.packages.service.converter.CommandArgConverterService;
import ru.hofftech.console.packages.service.factory.ParserBoxesServiceFactory;

import java.util.List;

/**
 * Сервис для выполнения команды импорта коробок.
 */
@Service
@RequiredArgsConstructor
public class ImportCommandService implements CommandExecutor {
    private final ParserBoxesServiceFactory parserBoxesServiceFactory;
    private final BoxRepository boxRepository;
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
                .create(boxRepository, command.consoleCommand(),
                        commandArgConverterService.fileToPath(
                                command.arguments().get(Argument.IMPORT_FILENAME)));
        return "Посылки загружены Кол-во " + result.size();
    }
}
