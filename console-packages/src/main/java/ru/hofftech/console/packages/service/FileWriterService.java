package ru.hofftech.console.packages.service;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterService {
    public void writeToFile(String content, String fileName)  {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
