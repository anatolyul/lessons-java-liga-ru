package ru.hofftech.consolepackages.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.consolepackages.exception.FileReadException;
import ru.hofftech.consolepackages.model.Box;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.repository.BoxRepository;
import ru.hofftech.consolepackages.service.ParserBoxesService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для парсинга информации о коробках из JSON файла.
 */
@Service
@RequiredArgsConstructor
public class ParserBoxesServiceJson implements ParserBoxesService {
    private final BoxRepository boxRepository;

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
            List<Truck> trucks = new ObjectMapper().readValue(
                    new File(filePath),
                    new TypeReference<>() {
                    });

            boxes = trucks.stream()
                    .map(Truck::getBoxes)
                    .filter(Objects::nonNull)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileReadException("Ошибка чтения файла: " + filePath, e);
        }

        if (!boxes.isEmpty()) {
            boxRepository.setBoxes(boxes);
        }

        return boxes;
    }
}
