package ru.hofftech.logisticservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.exception.FileReadException;
import ru.hofftech.logisticservice.exception.FileWriteException;
import ru.hofftech.logisticservice.model.Truck;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Сервис для разгрузки грузовиков и сохранения информации о коробках.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UnloaderTrucksToBoxesService {

    /**
     * Метод для разгрузки грузовиков и сохранения информации о коробках.
     *
     * @param fileNameTrucks имя файла, содержащего информацию о грузовиках
     * @param fileNameBoxes  имя файла, в который будет сохранена информация о коробках
     * @param withCount      флаг, указывающий, нужно ли включать количество коробок
     * @return строка с результатом операции
     */
    @SneakyThrows
    public String unloadTrucksToBoxes(String fileNameTrucks, String fileNameBoxes, boolean withCount) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String[]> boxes;

        try {
            List<Truck> trucks = objectMapper.readValue(
                    new File(fileNameTrucks),
                    new TypeReference<>() {
                    });

            boxes = trucks.stream()
                    .filter(truck -> truck.getBoxes() != null && !truck.getBoxes().isEmpty())
                    .map(Truck::getBoxes)
                    .flatMap(List::stream)
                    .map(BoxDto::getName)
                    .map(name -> new String[]{name})
                    .toList();

            if (withCount) {
                Map<String, Long> boxCounts = boxes.stream()
                        .map(arr -> arr[0])
                        .collect(Collectors.groupingBy(boxName -> boxName, Collectors.counting()));

                boxes = boxCounts.entrySet().stream()
                        .map(entry -> new String[]{entry.getKey(), String.valueOf(entry.getValue())})
                        .toList();
            }
        } catch (IOException e) {
            throw new FileReadException("Ошибка чтения файла грузовиков: " + fileNameTrucks, e);
        }

        StringBuilder result = new StringBuilder();

        if (fileNameBoxes != null && !fileNameBoxes.isEmpty()) {
            try (CSVWriter csvWriter = new CSVWriter(new FileWriter(fileNameBoxes))) {
                csvWriter.writeAll(boxes);
                result.append("Результаты сохранены в файл: ").append(fileNameBoxes).append("\n");
            } catch (IOException e) {
                throw new FileWriteException("Ошибка сохранения результатов в файл: " + fileNameBoxes, e);
            }
        } else {
            result.append(boxes.stream().map(arr -> arr[0]).collect(Collectors.joining("\n")));
        }

        return result.toString();
    }
}
