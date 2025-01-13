package ru.hofftech.console.packages.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Сервис для форматирования данных о грузовиках и коробках.
 */
public class FormatterService {

    /**
     * Преобразует список грузовиков в строку.
     *
     * @param trucks список грузовиков
     * @return строка, представляющая список грузовиков
     */
    public String TrucksToString(List<Truck> trucks) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        for (Truck truck : trucks) {
            sb.append(truck.getTruckName())
                    .append(":\n")
                    .append(truck.printCargo())
                    .append("\n");
        }

        return sb.toString();
    }

    /**
     * Преобразует список грузовиков в JSON строку.
     *
     * @param trucks список грузовиков
     * @return JSON строка, представляющая список грузовиков
     */
    public String TrucksToJson(List<Truck> trucks) {
        String result;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            result = objectMapper.writeValueAsString(trucks);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * Преобразует список коробок в строку.
     *
     * @param boxes список коробок
     * @return строка, представляющая список коробок
     */
    public String BoxesToString(List<Box> boxes) {
        StringBuilder sb = new StringBuilder();

        for (Box box : boxes) {
            for (int j = 0; j < box.getHeight(); j++) {
                sb.append(box.getSymbol().repeat(box.getWidth())).append("\n");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Преобразует команду импорта файла в путь к файлу.
     *
     * @param fileName команда импорта файла
     * @return путь к файлу
     */
    public String FileNameCommandToPath(String fileName) {
        final Pattern IMPORT_COMMAND_PATTERN = Pattern.compile("import (.+\\.(txt|json))");
        String result;
        Matcher matcher = IMPORT_COMMAND_PATTERN.matcher(fileName);
        fileName = matcher.matches() ? matcher.group(1) : fileName;
        result = FileToPath(fileName);
        return result;
    }

    /**
     * Преобразует имя файла в путь к файлу.
     *
     * @param fileName имя файла
     * @return путь к файлу
     */
    public String FileToPath(String fileName) {
        String result;
        if (new File(fileName).isFile()) {
            result = fileName;
        } else {
            result = Objects.requireNonNull(
                    FormatterService.class.getClassLoader()
                            .getResource(fileName)).getPath();
        }
        return result;
    }
}