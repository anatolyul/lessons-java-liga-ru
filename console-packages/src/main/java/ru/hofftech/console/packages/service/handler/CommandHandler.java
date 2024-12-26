package ru.hofftech.console.packages.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.model.converter.ConsoleCommandConverter;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;
import ru.hofftech.console.packages.service.FileWriterService;
import ru.hofftech.console.packages.service.FormatterService;
import ru.hofftech.console.packages.service.factory.LoaderBoxesInTrucksServiceFactory;
import ru.hofftech.console.packages.service.factory.ParserBoxesServiceFactory;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CommandHandler {
    private final ConsoleCommandConverter consoleCommandConverter;
    private final FormatterService formatterService;
    private final FileWriterService fileWriterService;
    private final ParserBoxesServiceFactory parserBoxesServiceFactory;
    private final LoaderBoxesInTrucksServiceFactory loaderBoxesInTrucksServiceFactory;

    private int limitTrucks = 1;
    private List<Box> boxes;
    private List<Truck> trucks;

    public void handle(String commandString) {
        ConsoleCommand command = consoleCommandConverter.convertStringToEnum(commandString);

        switch (command) {
            case EXIT -> System.exit(0);
            case IMPORT_FILE_TXT,
                 IMPORT_FILE_JSON -> {
                boxes = parserBoxesServiceFactory
                            .create(command)
                            .parse(formatterService.FileNameCommandToPath(commandString));
                if (!boxes.isEmpty()) {
                    log.info("""
                    
                            Выбор алгоритма погрузки:
                            1 - простой (одна посылка = одна машина)
                            2 - сложный (оптимальное размещение нескольких посылок по машинам)
                            3 - равномерная погрузка по машинам
                            """);
                }
            }
            case LIMIT -> limitTrucks = formatterService.LimitCommandToInt(commandString);
            case FIRST_ALGORITHM,
                 SECOND_ALGORITHM,
                 THIRD_ALGORITHM-> {
                trucks = loaderBoxesInTrucksServiceFactory
                            .createLoaderBoxesInTrucksService(command)
                            .loadBoxesInTrucks(boxes, limitTrucks);
            }
            case UNKNOWN -> log.error("Приложение не поддерживает данную команду.");
        }

        if (boxes != null && !boxes.isEmpty()
            && trucks != null && !trucks.isEmpty()) {
            log.info("""
                            
                        Результаты распределения груза:
                        """);
            log.info("{}", formatterService.TrucksToString(trucks));
            fileWriterService.writeToFile(formatterService.BoxesToString(boxes), "boxes_result.txt");
            fileWriterService.writeToFile(formatterService.TrucksToJson(trucks), "trucks_result.json");
            log.info("""
                           
                        Для повторного распределение определите выбор алгоритма погрузки:
                        """);
        }
    }
}
