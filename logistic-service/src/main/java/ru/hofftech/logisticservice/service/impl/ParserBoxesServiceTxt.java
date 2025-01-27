package ru.hofftech.logisticservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.exception.FileReadException;
import ru.hofftech.logisticservice.service.BoxService;
import ru.hofftech.logisticservice.service.ParserBoxesService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация сервиса для парсинга информации о коробках из текстового файла.
 */
@Service
@RequiredArgsConstructor
public class ParserBoxesServiceTxt implements ParserBoxesService {
    private final BoxService boxService;

    /**
     * Парсит информацию о коробках из текстового файла.
     *
     * @param filePath путь к файлу, содержащему информацию о коробках
     * @return список коробок, полученных из файла
     * @throws RuntimeException если произошла ошибка ввода-вывода
     */
    @Override
    public List<BoxDto> parse(String filePath) {
        List<BoxDto> boxes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String symbol = "";
            StringBuilder form = new StringBuilder();

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    if (!symbol.isEmpty()) {
                        BoxDto box = new BoxDto(
                                "Посылка Тип " + symbol,
                                form.substring(0, form.length() - 1),
                                symbol);

                        boxes.add(box);
                        form = new StringBuilder();
                        symbol = "";
                    }
                } else {
                    symbol = line.substring(0, 1);
                    form.append(line).append("\n");
                }
            }

            if (!symbol.isEmpty()) {
                BoxDto box = new BoxDto(
                        "Посылка Тип " + symbol,
                        form.substring(0, form.length() - 1),
                        symbol);
                boxes.add(box);

            }

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
