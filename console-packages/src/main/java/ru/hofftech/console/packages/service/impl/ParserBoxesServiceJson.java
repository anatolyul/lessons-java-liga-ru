package ru.hofftech.console.packages.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.service.ParserBoxesService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class ParserBoxesServiceJson implements ParserBoxesService {
    private final ObjectMapper objectMapper;

    @Override
    public List<Box> parse(String filePath) {
        List<Box> boxes;

        try {
            List<Truck> trucks = objectMapper.readValue(
                    new File(filePath),
                    new TypeReference<>() {
                    });

            boxes = trucks.stream().map(Truck::getBoxes).flatMap(List::stream).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!boxes.isEmpty()) {
            log.info("""
                    
                            Выбор алгоритма погрузки:
                            1 - простой (одна посылка = одна машина)
                            2 - сложный (оптимальное размещение нескольких посылок по машинам)
                            3 - равномерная погрузка по машинам
                            """);
        }

        return boxes;
    }
}
