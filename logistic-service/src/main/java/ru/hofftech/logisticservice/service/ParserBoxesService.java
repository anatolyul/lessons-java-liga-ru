package ru.hofftech.logisticservice.service;

import ru.hofftech.logisticservice.dto.BoxDto;

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
    List<BoxDto> parse(String filePath);
}
