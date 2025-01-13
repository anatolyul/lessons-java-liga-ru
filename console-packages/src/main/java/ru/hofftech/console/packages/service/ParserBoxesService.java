package ru.hofftech.console.packages.service;

import ru.hofftech.console.packages.model.Box;

import java.util.List;

/**
 * Интерфейс для парсинга информации о коробках из файла.
 */
public interface ParserBoxesService {

    /**
     * Метод для парсинга информации о коробках из файла.
     *
     * @param filePath путь к файлу, содержащему информацию о коробках
     * @return список коробок, полученных из файла
     */
    List<Box> parse(String filePath);
}
