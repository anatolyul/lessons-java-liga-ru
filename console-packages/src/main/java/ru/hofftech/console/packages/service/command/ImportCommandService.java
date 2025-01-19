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

@Service
@RequiredArgsConstructor
public class ImportCommandService implements CommandExecutor {
    private final ParserBoxesServiceFactory parserBoxesServiceFactory;
    private final BoxRepository boxRepository;
    private final CommandArgConverterService commandArgConverterService;

    /**
     * @param command
     * @return
     */
    @Override
    public String execute(Command command) {
        List<Box> result = parserBoxesServiceFactory
                .create(boxRepository, command.getConsoleCommand(),
                        commandArgConverterService.fileToPath(
                                command.getArguments().get(Argument.IMPORT_FILENAME)));
        return "Посылки загружены Кол-во " + result.size();
    }
}
