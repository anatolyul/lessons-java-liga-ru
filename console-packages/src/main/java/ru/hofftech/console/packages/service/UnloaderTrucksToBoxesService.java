package ru.hofftech.console.packages.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UnloaderTrucksToBoxesService {
    public boolean unloadTrucksToBoxes(String fileNameTrucks, String fileNameBoxes, boolean withcount) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String[]> boxes;

        try {
            List<Truck> trucks = objectMapper.readValue(
                    new File(fileNameTrucks),
                    new TypeReference<>() {
                    });

            boxes = trucks.stream()
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

        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(fileNameBoxes))) {
            csvWriter.writeAll(boxes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
