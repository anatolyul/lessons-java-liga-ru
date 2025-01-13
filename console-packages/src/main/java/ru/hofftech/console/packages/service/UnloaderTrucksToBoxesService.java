package ru.hofftech.console.packages.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class UnloaderTrucksToBoxesService {
    public String unloadTrucksToBoxes(String fileNameTrucks, String fileNameBoxes, boolean withcount) {
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
                    .map(Box::getName)
                    .map(name -> new String[]{name})
                    .collect(Collectors.toList());

            if (withcount) {
                Map<String, Long> boxCounts = boxes.stream()
                        .map(arr -> arr[0])
                        .collect(Collectors.groupingBy(boxName -> boxName, Collectors.counting()));

                boxes = boxCounts.entrySet().stream()
                        .map(entry -> new String[]{entry.getKey(), String.valueOf(entry.getValue())})
                        .collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StringBuilder result = new StringBuilder();

        if (fileNameBoxes != null && !fileNameBoxes.isEmpty()) {
            try (CSVWriter csvWriter = new CSVWriter(new FileWriter(fileNameBoxes))) {
                csvWriter.writeAll(boxes);
                result.append("Результаты сохранены в файл: ").append(fileNameBoxes);
            } catch (IOException e) {
                log.error("Ошибка сохранения результатов в файл", e);
            }
        } else {
            result.append(boxes.stream().map(arr -> arr[0]).collect(Collectors.joining("\n")));
        }

        return result.toString();
    }
}
