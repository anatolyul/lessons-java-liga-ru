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

    private static final int BOX_NAME_INDEX = 0;

    /**
     * Метод для разгрузки грузовиков и сохранения информации о коробках.
     *
     * @param trucks         данные по грузовикам
     * @param fileNameBoxes  имя файла, в который будет сохранена информация о коробках
     * @param withCount      флаг, указывающий, нужно ли включать количество коробок
     * @return строка с результатом операции
     */
    @SneakyThrows
    public List<String[]> unloadTrucksToBoxes(List<Truck> trucks, String fileNameBoxes, boolean withCount) {
        List<String[]> boxes = extractBoxesFromTrucks(trucks);

        if (withCount) {
            boxes = countBoxes(boxes);
        }

        if (fileNameBoxes != null && !fileNameBoxes.isEmpty()) {
            saveBoxesToFile(boxes, fileNameBoxes);
        }

        return boxes;
    }

    /**
     * Загружает грузовики из файла.
     *
     * @param fileNameTrucks имя файла с информацией о грузовиках
     * @return список грузовиков
     */
    public List<Truck> loadTrucksFromFile(String fileNameTrucks) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(fileNameTrucks), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new FileReadException("Ошибка чтения файла грузовиков: " + fileNameTrucks, e);
        }
    }

    /**
     * Извлекает коробки из грузовиков.
     *
     * @param trucks список грузовиков
     * @return список коробок
     */
    private List<String[]> extractBoxesFromTrucks(List<Truck> trucks) {
        return trucks.stream()
                .filter(truck -> truck.getBoxes() != null && !truck.getBoxes().isEmpty())
                .map(Truck::getBoxes)
                .flatMap(List::stream)
                .map(BoxDto::getName)
                .map(name -> new String[]{name})
                .toList();
    }

    /**
     * Подсчитывает количество каждой коробки.
     *
     * @param boxes список коробок
     * @return список коробок с количеством
     */
    private List<String[]> countBoxes(List<String[]> boxes) {
        Map<String, Long> boxCounts = boxes.stream()
                .map(arr -> arr[BOX_NAME_INDEX])
                .collect(Collectors.groupingBy(boxName -> boxName, Collectors.counting()));

        return boxCounts.entrySet().stream()
                .map(entry -> new String[]{entry.getKey(), String.valueOf(entry.getValue())})
                .toList();
    }

    /**
     * Сохраняет информацию о коробках в файл.
     *
     * @param boxes         список коробок
     * @param fileNameBoxes имя файла для сохранения
     */
    private void saveBoxesToFile(List<String[]> boxes, String fileNameBoxes) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(fileNameBoxes))) {
            csvWriter.writeAll(boxes);
        } catch (IOException e) {
            throw new FileWriteException("Ошибка сохранения результатов в файл: " + fileNameBoxes, e);
        }
    }
}
