package ru.hofftech.logisticservice.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.exception.FileReadException;
import ru.hofftech.logisticservice.model.Truck;
import ru.hofftech.logisticservice.service.BoxService;
import ru.hofftech.logisticservice.service.ParserBoxesService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Реализация сервиса для парсинга информации о коробках из JSON файла.
 */
@Service
@RequiredArgsConstructor
public class ParserBoxesServiceJson implements ParserBoxesService {
    private final BoxService boxService;

    /**
     * Парсит информацию о коробках из JSON файла.
     *
     * @param filePath путь к файлу, содержащему информацию о коробках в формате JSON
     * @return список коробок, полученных из файла
     * @throws RuntimeException если произошла ошибка ввода-вывода
     */
    @Override
    public List<BoxDto> parse(String filePath) {
        List<BoxDto> boxes;

        try {
            List<Truck> trucks = new ObjectMapper().readValue(
                    new File(filePath),
                    new TypeReference<>() {
                    });

            boxes = trucks.stream()
                    .map(Truck::getBoxes)
                    .filter(Objects::nonNull)
                    .flatMap(List::stream)
                    .toList();
        } catch (IOException e) {
            throw new FileReadException("Ошибка чтения файла: " + filePath, e);
        }

        if (!boxes.isEmpty()) {
            boxService.findAll()
                    .forEach(box -> boxService.delete(box.getName()));
            boxes.forEach(boxService::create);
        }

        return boxes;
    }
}
