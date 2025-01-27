package ru.hofftech.logisticservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.model.Truck;

import java.util.List;

/**
 * Сервис для сохранения результатов распределения груза.
 */
@Service
@RequiredArgsConstructor
public class ResultOutSaveService {
    private final FileWriterService fileWriterService;
    private final FormatterService formatterService;

    /**
     * Метод для сохранения результатов распределения груза.
     *
     * @param boxes            список коробок
     * @param fileNameResult   имя файла для сохранения результата
     * @return строка с результатом операции
     */
    public String saveOutResult(List<BoxDto> boxes, List<Truck> trucks, String fileNameResult) {
        StringBuilder result = new StringBuilder();

        if (boxes != null && !boxes.isEmpty()
                && trucks != null && !trucks.isEmpty()) {
            result.append("""
                    
                    Результаты распределения груза:
                    """);
            result.append(formatterService.trucksToString(trucks));
            fileWriterService.writeToFile(formatterService.boxesToString(boxes), "boxes_result.txt");
            if (fileNameResult != null && !fileNameResult.isBlank()) {
                fileWriterService.writeToFile(formatterService.trucksToJson(trucks), fileNameResult);
                result.append("Результат сохранен в файл: ").append(fileNameResult).append("\n");
            }
            List<BoxDto> boxesNotInTrucks = boxes.stream()
                    .filter(box ->
                            trucks.stream().filter(truck -> truck.getBoxes() != null)
                                    .flatMap(truck -> truck.getBoxes().stream())
                                    .noneMatch(truckBox -> truckBox.equals(box))
                    )
                    .toList();
            for (BoxDto box : boxesNotInTrucks) {
                result.append("Пропущена посылка: ")
                        .append(box.getName())
                        .append("\n");
            }
        }

        return result.toString();
    }
}
