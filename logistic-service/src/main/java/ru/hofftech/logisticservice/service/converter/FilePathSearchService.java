package ru.hofftech.logisticservice.service.converter;

import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.service.FormatterService;

import java.io.File;
import java.util.Objects;

/**
 * Конвертер для преобразования строковых представлений команд и аргументов в соответствующие перечисления.
 */
@Service
public class FilePathSearchService {

    /**
     * Преобразует имя файла в путь к файлу.
     *
     * @param fileName имя файла
     * @return путь к файлу
     */
    public String search(String fileName) {
        String result;
        if (new File(fileName).isFile()) {
            result = fileName;
        } else {
            result = Objects.requireNonNull(FormatterService.class.getClassLoader()
                    .getResource(fileName)).getPath();
        }
        return result;
    }
}
