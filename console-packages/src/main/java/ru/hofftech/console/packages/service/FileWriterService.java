package ru.hofftech.console.packages.service;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Сервис для записи данных в файл.
 */
@Service
public class FileWriterService {

    /**
     * Записывает содержимое в файл.
     *
     * @param content  содержимое, которое нужно записать в файл
     * @param fileName имя файла, в который нужно записать содержимое
     * @throws RuntimeException если произошла ошибка ввода-вывода
     */
    public void writeToFile(String content, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
