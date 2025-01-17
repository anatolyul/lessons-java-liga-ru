package ru.hofftech.console.packages.service.factory;

import lombok.RequiredArgsConstructor;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.service.CommandExecutor;
import ru.hofftech.console.packages.service.FormatterService;
import ru.hofftech.console.packages.service.ResultOutSaveService;
import ru.hofftech.console.packages.service.UnloaderTrucksToBoxesService;
import ru.hofftech.console.packages.service.command.BoxCreateCommandService;
import ru.hofftech.console.packages.service.command.BoxDeleteCommandService;
import ru.hofftech.console.packages.service.command.BoxEditCommandService;
import ru.hofftech.console.packages.service.command.BoxFindCommandService;
import ru.hofftech.console.packages.service.command.BoxListCommandService;
import ru.hofftech.console.packages.service.command.ExitCommandService;
import ru.hofftech.console.packages.service.command.ImportCommandService;
import ru.hofftech.console.packages.service.command.LoadCommandService;
import ru.hofftech.console.packages.service.command.UnknownCommandService;
import ru.hofftech.console.packages.service.command.UnloadCommandService;
import ru.hofftech.console.packages.service.converter.CommandArgConverterService;

@RequiredArgsConstructor
public class CommandExecutorFactory {
    private final CommandArgConverterService commandArgConverterService;
    private final FormatterService formatterService;
    private final ParserBoxesServiceFactory parserBoxesServiceFactory;
    private final LoaderBoxesInTrucksServiceFactory loaderBoxesInTrucksServiceFactory;
    private final UnloaderTrucksToBoxesService unloaderTrucksToBoxesService;
    private final BoxRepository boxRepository;
    private final ResultOutSaveService resultOutSaveService;

    public CommandExecutor create(ConsoleCommand command) {
        return
                switch (command) {
                    case EXIT -> new ExitCommandService();
                    case IMPORT_FILE_TXT, IMPORT_FILE_JSON -> new ImportCommandService(parserBoxesServiceFactory,
                            boxRepository);
                    case LOAD -> new LoadCommandService(loaderBoxesInTrucksServiceFactory,
                            commandArgConverterService,
                            formatterService,
                            resultOutSaveService,
                            parserBoxesServiceFactory,
                            boxRepository);
                    case UNLOAD -> new UnloadCommandService(unloaderTrucksToBoxesService, commandArgConverterService);
                    case BOX_CREATE -> new BoxCreateCommandService(boxRepository);
                    case BOX_FIND -> new BoxFindCommandService(boxRepository);
                    case BOX_DELETE -> new BoxDeleteCommandService(boxRepository);
                    case BOX_EDIT -> new BoxEditCommandService(boxRepository);
                    case BOX_LIST -> new BoxListCommandService(boxRepository);
                    case UNKNOWN -> new UnknownCommandService();
                };
    }
}
