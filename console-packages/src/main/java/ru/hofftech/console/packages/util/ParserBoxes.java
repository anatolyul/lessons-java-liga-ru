package ru.hofftech.console.packages.util;

import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ParserBoxes {
    private String getFilePath(String fileName) {
        final Pattern IMPORT_COMMAND_PATTERN = Pattern.compile("import (.+\\.txt)");
        String result;
        Matcher matcher = IMPORT_COMMAND_PATTERN.matcher(fileName);
        fileName = matcher.matches() ? matcher.group(1) : fileName;
        result = Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();

        return result;
    }

    public List<Box> parseFromFile(String fileName) {
        List<Box> boxes = new ArrayList<>();
        String filePath = getFilePath(fileName);

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
            log.error(e.getMessage(), e);
        }

        return boxes;
    }
}
