package ru.hofftech.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.model.Box;
import ru.hofftech.model.Truck;
import ru.hofftech.service.LoadingBoxesInTruckService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.hofftech.util.ImportBoxes.parseFromFile;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {

    private final Pattern IMPORT_COMMAND_PATTERN = Pattern.compile("import (.+\\.txt)");

    public void listen() {
        var scanner = new Scanner(System.in);
        List<Box> boxes = new ArrayList<>();

        log.info("""

                Справочник команд:
                import boxes.txt - загрузка файла с посылками
                exit - завершение работы
                
                Выбор алгоритма погрузки:
                1 - простой (одна посылка = одна машина)
                2 - сложный (оптимальное размещение нескольких посылок по машинам)
                """);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            if (command.equals("exit")) {
                System.exit(0);
            }

            Matcher matcher = IMPORT_COMMAND_PATTERN.matcher(command);

            if (matcher.matches()) {
                String filePath = matcher.group(1);
                boxes = parseFromFile(Objects.requireNonNull(getClass().getClassLoader().getResource(filePath)).getPath());
            }

            if (!boxes.isEmpty()) {
                log.info("""
                        
                        Выбор алгоритма погрузки:
                        1 - простой (одна посылка = одна машина)
                        2 - сложный (оптимальное размещение нескольких посылок по машинам)
                        """);

                if (command.equals("1") || command.equals("2"))
                {
                    LoadingBoxesInTruckService loadingBoxesInTruckService = new LoadingBoxesInTruckService();
                    List<Truck> trucks;

                    if (command.equals("1")) {
                        trucks = loadingBoxesInTruckService.simpleAlg(boxes);
                    }
                    else {
                        trucks = loadingBoxesInTruckService.complexAlg(boxes);
                    }

                    log.info("""
                            
                            Результаты распределения груза:
                            """);
                    loadingBoxesInTruckService.report(trucks);

                    log.info("""
                            
                            Для повторного распределение определите выбор алгоритма погрузки:
                            """);
                }
            }
        }
    }
}
