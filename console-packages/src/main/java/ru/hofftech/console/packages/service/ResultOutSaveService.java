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

    public String saveOutResult(FormatterService formatterService,
                              List<Truck> trucks, List<Box> boxes) {
        StringBuilder result = new StringBuilder();

        if (boxes != null && !boxes.isEmpty()
                && trucks != null && !trucks.isEmpty()) {
            result.append("""
                            
                        Результаты распределения груза:
                        """);
            result.append(formatterService.TrucksToString(trucks));
            fileWriterService.writeToFile(formatterService.BoxesToString(boxes), "boxes_result.txt");
            fileWriterService.writeToFile(formatterService.TrucksToJson(trucks), "trucks_result.json");
            result.append("""
                           
                        Для повторного распределение определите выбор алгоритма погрузки:
                        """);
        }

        return result.toString();
    }
}
