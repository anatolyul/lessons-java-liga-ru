package ru.hofftech.console.packages.util.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.util.ParserBoxes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ParserBoxesJson implements ParserBoxes {
    private final ObjectMapper objectMapper;

    @Override
    public List<Box> parse(String filePath) {
        List<Box> boxes;

        try {
            List<Truck> trucks = objectMapper.readValue(
                    new File(filePath),
                    new TypeReference<List<Truck>>(){});

            boxes = trucks.stream().map(Truck::getBoxes).flatMap(List::stream).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return boxes;
    }
}
