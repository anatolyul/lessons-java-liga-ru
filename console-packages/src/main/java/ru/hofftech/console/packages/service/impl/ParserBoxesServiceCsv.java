package ru.hofftech.console.packages.service.impl;

import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.service.ParserBoxesService;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class ParserBoxesServiceCsv implements ParserBoxesService {
    private final BoxRepository boxRepository;

    @Override
    public List<Box> parse(String filePath) {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            List<String> boxNames = csvReader.readAll()
                    .stream()
                    .map(array -> array[0])
                    .toList();
            return boxRepository.getBoxes()
                    .stream()
                    .filter(box -> boxNames.contains(box.getName()))
                    .toList();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
