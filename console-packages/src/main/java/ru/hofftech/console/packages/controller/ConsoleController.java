package ru.hofftech.console.packages.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.ConsoleCommand;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.model.converter.ConsoleCommandConverter;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;
import ru.hofftech.console.packages.service.impl.LoaderBoxesInTrucksFirstAlgService;
import ru.hofftech.console.packages.service.FormatterService;
import ru.hofftech.console.packages.service.impl.LoaderBoxesInTrucksSecondAlgService;
import ru.hofftech.console.packages.util.ParserBoxes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {
    private final ParserBoxes parserBoxes;
    private final ConsoleCommandConverter consoleCommandConverter;


    public void listen() {
        var scanner = new Scanner(System.in);
        log.info("""
                
                Старт приложения Консольные посылки...

                Справочник команд:
                import boxes.txt - загрузка файла с посылками
                exit - завершение работы
                
                Выбор алгоритма погрузки:
                1 - простой (одна посылка = одна машина)
                2 - сложный (оптимальное размещение нескольких посылок по машинам)
                """);
        List<Box> boxes = new ArrayList<>();
        while (scanner.hasNextLine()) {
            List<Truck> trucks = new ArrayList<>();
            String commandString = scanner.nextLine();
            ConsoleCommand command = consoleCommandConverter.convertStringToEnum(commandString);

            switch (command) {
                case EXIT -> System.exit(0);
                case IMPORT_FILE -> {
                    boxes = parserBoxes.parseFromFile(commandString);
                    if (!boxes.isEmpty()) {
                        log.info("""
                    
                            Выбор алгоритма погрузки:
                            1 - простой (одна посылка = одна машина)
                            2 - сложный (оптимальное размещение нескольких посылок по машинам)
                            """);
                    }
                }
                case FIRST_ALGORITHM -> {
                    LoaderBoxesInTrucksService loadingBoxesInTruckService = new LoaderBoxesInTrucksFirstAlgService();
                    trucks = loadingBoxesInTruckService.loadBoxesInTrucks(boxes);
                }
                case SECOND_ALGORITHM -> {
                    LoaderBoxesInTrucksService loadingBoxesInTruckService = new LoaderBoxesInTrucksSecondAlgService();
                    trucks = loadingBoxesInTruckService.loadBoxesInTrucks(boxes);
                }
                case UNKNOWN -> log.error("Приложение не поддерживает данную команду.");
            }

            if (!boxes.isEmpty() && !trucks.isEmpty()) {
                log.info("""
                            
                            Результаты распределения груза:
                            """);
                log.info("{}", FormatterService.TrucksToString(trucks));
                log.info("""
                            
                            Для повторного распределение определите выбор алгоритма погрузки:
                            """);
            }
        }
    }
}
