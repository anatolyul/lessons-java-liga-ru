package ru.hofftech.logisticservice.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.exception.FileReadException;
import ru.hofftech.logisticservice.service.BoxService;
import ru.hofftech.logisticservice.service.ParserBoxesService;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Реализация сервиса для парсинга информации о коробках из CSV файла.
 */
@Service
@RequiredArgsConstructor
public class ParserBoxesServiceCsv implements ParserBoxesService {
    private final BoxService boxService;
    private static final int BOX_NAME_INDEX = 0;

    /**
     * Парсит информацию о коробках из CSV файла.
     *
     * @param filePath путь к файлу, содержащему информацию о коробках в формате CSV
     * @return список коробок, полученных из файла
     * @throws RuntimeException если произошла ошибка ввода-вывода или ошибка парсинга CSV
     */
    @Override
    public List<BoxDto> parse(String filePath) {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            List<String> boxNames = csvReader.readAll()
                    .stream()
                    .map(array -> array[BOX_NAME_INDEX])
                    .toList();
            return boxService.findAll()
                    .stream()
                    .filter(box -> boxNames.contains(box.getName()))
                    .toList();
        } catch (IOException | CsvException e) {
            throw new FileReadException("Ошибка чтения файла: " + filePath, e);
        }
    }
}
