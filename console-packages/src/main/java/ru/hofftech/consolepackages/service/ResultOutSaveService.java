package ru.hofftech.consolepackages.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.consolepackages.model.Box;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.repository.TruckRepository;

import java.util.List;

/**
 * Сервис для сохранения результатов распределения груза.
 */
@Service
@RequiredArgsConstructor
public class ResultOutSaveService {
    private final FileWriterService fileWriterService;
    private final TruckRepository truckRepository;

    /**
     * Метод для сохранения результатов распределения груза.
     *
     * @param formatterService сервис для форматирования данных
     * @param boxes            список коробок
     * @param fileNameResult   имя файла для сохранения результата
     * @return строка с результатом операции
     */
    public String saveOutResult(FormatterService formatterService, List<Box> boxes, String fileNameResult) {
        StringBuilder result = new StringBuilder();
        List<Truck> trucks = truckRepository.getTrucks();

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
