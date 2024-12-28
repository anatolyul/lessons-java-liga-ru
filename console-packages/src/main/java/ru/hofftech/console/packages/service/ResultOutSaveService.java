package ru.hofftech.console.packages.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ResultOutSaveService {
    private final FileWriterService fileWriterService;

    public void saveOutResult(FormatterService formatterService,
                              List<Truck> trucks, List<Box> boxes) {
        if (boxes != null && !boxes.isEmpty()
                && trucks != null && !trucks.isEmpty()) {
            log.info("""
                            
                        Результаты распределения груза:
                        """);
            var resultTruckJson = formatterService.TrucksToString(trucks);
            log.info("{}", resultTruckJson);
            fileWriterService.writeToFile(formatterService.BoxesToString(boxes), "boxes_result.txt");
            fileWriterService.writeToFile(resultTruckJson, "trucks_result.json");
            log.info("""
                           
                        Для повторного распределение определите выбор алгоритма погрузки:
                        """);
        }
    }
}
