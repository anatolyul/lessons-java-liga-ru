package ru.hofftech.consolepackages.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.consolepackages.model.Box;
import ru.hofftech.consolepackages.repository.BoxRepository;
import ru.hofftech.consolepackages.service.ParserBoxesService;

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
    private final BoxRepository boxRepository;

    /**
     * Парсит информацию о коробках из текстового файла.
     *
     * @param filePath путь к файлу, содержащему информацию о коробках
     * @return список коробок, полученных из файла
     * @throws RuntimeException если произошла ошибка ввода-вывода
     */
    @Override
    public List<Box> parse(String filePath) {
        List<Box> boxes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String content = "";
            int width = 0;
            int height = 0;

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    if (!content.isEmpty()) {
                        Box box = new Box(width, height, content);

                        if (box.isValid()) {
                            boxes.add(box);
                        }
                        content = "";
                        width = 0;
                        height = 0;
                    }
                } else {
                    content = line.substring(0, 1);
                    width = line.length();
                    height++;
                }
            }

            if (!content.isEmpty()) {
                Box box = new Box(width, height, content);

                if (box.isValid()) {
                    boxes.add(box);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!boxes.isEmpty()) {
            boxRepository.setBoxes(boxes);
        }

        return boxes;
    }
}
