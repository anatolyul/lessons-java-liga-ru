package ru.hofftech.console.packages.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.model.converter.ConsoleCommandConverter;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;
import ru.hofftech.console.packages.service.FormatterService;
import ru.hofftech.console.packages.service.ResultOutSaveService;
import ru.hofftech.console.packages.service.factory.LoaderBoxesInTrucksServiceFactory;
import ru.hofftech.console.packages.service.factory.ParserBoxesServiceFactory;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CommandHandler {
    private final ConsoleCommandConverter consoleCommandConverter;
    private final FormatterService formatterService;
    private final ParserBoxesServiceFactory parserBoxesServiceFactory;
    private final LoaderBoxesInTrucksServiceFactory loaderBoxesInTrucksServiceFactory;
    private final ResultOutSaveService resultOutSaveService;

    private int limitTrucks = 1;
    private List<Box> boxes;

    public void handle(String commandString) {
        ConsoleCommand command = consoleCommandConverter.convertStringToEnum(commandString);

        switch (command) {
            case EXIT -> System.exit(0);
            case IMPORT_FILE_TXT,
                 IMPORT_FILE_JSON -> {
                boxes = parserBoxesServiceFactory
                            .create(command)
                            .parse(formatterService.FileNameCommandToPath(commandString));
            }
            case LIMIT -> limitTrucks = formatterService.LimitCommandToInt(commandString);
            case FIRST_ALGORITHM,
                 SECOND_ALGORITHM,
                 THIRD_ALGORITHM-> {
                List<Truck> trucks = loaderBoxesInTrucksServiceFactory
                        .createLoaderBoxesInTrucksService(command)
                        .loadBoxesInTrucks(boxes, limitTrucks);
                resultOutSaveService.saveOutResult(formatterService, trucks, boxes);
            }
            case UNKNOWN -> log.error("Приложение не поддерживает данную команду.");
        }
    }
}
