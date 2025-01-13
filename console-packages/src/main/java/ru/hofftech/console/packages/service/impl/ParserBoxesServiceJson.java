package ru.hofftech.console.packages.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.service.ParserBoxesService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для парсинга информации о коробках из JSON файла.
 */
@Slf4j
@RequiredArgsConstructor
public class ParserBoxesServiceJson implements ParserBoxesService {
    private final BoxRepository boxRepository;
    private final ObjectMapper objectMapper;

    /**
     * Парсит информацию о коробках из JSON файла.
     *
     * @param filePath путь к файлу, содержащему информацию о коробках в формате JSON
     * @return список коробок, полученных из файла
     * @throws RuntimeException если произошла ошибка ввода-вывода
     */
    @Override
    public List<Box> parse(String filePath) {
        List<Box> boxes;

        try {
            List<Truck> trucks = objectMapper.readValue(
                    new File(filePath),
                    new TypeReference<>() {
                    });

            boxes = trucks.stream()
                    .map(Truck::getBoxes)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!boxes.isEmpty()) {
            boxRepository.setBoxes(boxes);
        }

        return boxes;
    }
}
