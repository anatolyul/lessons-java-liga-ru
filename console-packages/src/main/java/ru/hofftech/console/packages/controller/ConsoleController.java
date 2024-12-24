package ru.hofftech.console.packages.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.model.converter.ConsoleCommandConverter;
import ru.hofftech.console.packages.service.FileWriterService;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksServiceFactory;
import ru.hofftech.console.packages.service.FormatterService;
import ru.hofftech.console.packages.util.ParserBoxes;
import ru.hofftech.console.packages.util.ParserBoxesFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {
    private final ConsoleCommandConverter consoleCommandConverter;
    private final FileWriterService fileWriterService;
    private final FormatterService formatterService;

    public void listen() {
        var scanner = new Scanner(System.in);
        log.info("""
                
                Старт приложения Консольные посылки...

                Справочник команд:
                import boxes.txt - загрузка файла с посылками
                import trucks.json - загрузка файла с грузовиками
                limit 5 - максимальное кол-во машин для погрузки
                exit - завершение работы
                
                Выбор алгоритма погрузки:
                1 - простой (одна посылка = одна машина)
                2 - сложный (оптимальное размещение нескольких посылок по машинам)
                """);

        List<Box> boxes = new ArrayList<>();
        int limitTrucks = 0;

        while (scanner.hasNextLine()) {
            List<Truck> trucks = new ArrayList<>();
            String commandString = scanner.nextLine();
            ConsoleCommand command = consoleCommandConverter.convertStringToEnum(commandString);

            switch (command) {
                case EXIT -> System.exit(0);
                case IMPORT_FILE_TXT,
                     IMPORT_FILE_JSON -> {
                    ParserBoxes parserBoxes = ParserBoxesFactory.createParserBoxes(command);
                    boxes = parserBoxes.parse(formatterService.FileNameCommandToPath(commandString));
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
                    LoaderBoxesInTrucksService loadingBoxesInTruckService
                            = LoaderBoxesInTrucksServiceFactory.createLoaderBoxesInTrucksService(command);
                    trucks = loadingBoxesInTruckService.loadBoxesInTrucks(boxes, limitTrucks);
                }
                case UNKNOWN -> log.error("Приложение не поддерживает данную команду.");
            }

            if (!boxes.isEmpty() && !trucks.isEmpty()) {
                log.info("""
                            
                            Результаты распределения груза:
                            """);
                log.info("{}", formatterService.TrucksToString(trucks));
                //log.info("{}", formatterService.TrucksToJson(trucks));
                fileWriterService.writeToFile(formatterService.BoxesToString(boxes), "boxes_result.txt");
                fileWriterService.writeToFile(formatterService.TrucksToJson(trucks), "trucks_result.json");
                log.info("""
                            
                            Для повторного распределение определите выбор алгоритма погрузки:
                            """);
            }
        }
    }
}
