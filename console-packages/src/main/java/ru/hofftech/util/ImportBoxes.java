package ru.hofftech.util;

import lombok.extern.slf4j.Slf4j;
import ru.hofftech.model.Box;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ImportBoxes {
    public static List<Box> parseFromFile(String fileName) {
        List<Box> boxes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
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
            log.error(e.getMessage(), e);
        }

        return boxes;
    }
}
