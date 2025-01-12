package ru.hofftech.console.packages.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.service.ParserBoxesService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ParserBoxesServiceTxt implements ParserBoxesService {
    private final BoxRepository boxRepository;

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
                    content = line.substring(0,1);
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
