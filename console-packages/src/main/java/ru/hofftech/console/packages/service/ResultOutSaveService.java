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
                              List<Truck> trucks, List<Box> boxes, String fileNameResult) {
        StringBuilder result = new StringBuilder();

        if (boxes != null && !boxes.isEmpty()
                && trucks != null && !trucks.isEmpty()) {
            result.append("""
                        
                        Результаты распределения груза:
                        """);
            result.append(formatterService.TrucksToString(trucks));
            fileWriterService.writeToFile(formatterService.BoxesToString(boxes), "boxes_result.txt");
            if (fileNameResult != null) {
                fileWriterService.writeToFile(formatterService.TrucksToJson(trucks), fileNameResult);
                result.append("Результат сохранен в файл: ").append(fileNameResult);
            }
            List<Box> boxesNotInTrucks = boxes.stream()
                    .filter(box ->
                            trucks.stream().filter(truck -> truck.getBoxes() != null)
                                    .flatMap(truck -> truck.getBoxes().stream())
                            .noneMatch(truckBox -> truckBox.equals(box))
                    )
                    .toList();
            for (Box box : boxesNotInTrucks) {
                result.append("Пропущена посылка: ")
                        .append(box.getName())
                        .append("\n");
            }
        }

        return result.toString();
    }
}
