package ru.hofftech.logisticcliservice.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;
import ru.hofftech.logisticcliservice.dto.BoxDto;
import ru.hofftech.logisticcliservice.dto.TruckDto;
import ru.hofftech.logisticcliservice.dto.command.BoxCreateCommandDto;
import ru.hofftech.logisticcliservice.dto.command.BoxEditCommandDto;
import ru.hofftech.logisticcliservice.dto.command.ImportCommandDto;
import ru.hofftech.logisticcliservice.dto.command.LoadCommandDto;
import ru.hofftech.logisticcliservice.dto.command.UnloadCommandDto;

import java.util.List;

/**
 * Интерфейс для взаимодействия с сервисом логистики.
 */
public interface LogisticService {

    /**
     * Получает список всех коробок.
     *
     * @return список всех коробок
     */
    @GetExchange("/api/v1/boxes")
    List<BoxDto> findAllBoxes();

    /**
     * Получает коробку по имени.
     *
     * @param name имя коробки
     * @return коробка с указанным именем
     */
    @GetExchange("/api/v1/boxes/{name}")
    BoxDto findBoxByName(@PathVariable String name);

    /**
     * Создает новую коробку.
     *
     * @param box данные новой коробки
     * @return созданная коробка
     */
    @PostExchange("/api/v1/boxes")
    BoxDto createBox(@RequestBody BoxCreateCommandDto box);

    /**
     * Обновляет существующую коробку.
     *
     * @param box  новые данные коробки
     * @return обновленная коробка
     */
    @PutExchange("/api/v1/boxes")
    BoxDto updateBox(@RequestBody BoxEditCommandDto box);

    /**
     * Удаляет коробку по имени.
     *
     * @param name имя коробки
     * @return результат удаления
     */
    @DeleteExchange("/api/v1/boxes/{name}")
    Boolean deleteBoxByName(@PathVariable String name);

    /**
     * Загружает коробки в грузовики.
     *
     * @param loadCommandDto параметры загрузки
     * @return список всех коробок
     */
    @PostExchange("/api/v1/boxes/action/load")
    List<TruckDto> loadBoxes(@RequestBody LoadCommandDto loadCommandDto);

    /**
     * Разгружает грузовики.
     *
     * @param unloadCommandDto параметры разгрузки
     * @return список всех коробок
     */
    @PostExchange("/api/v1/boxes/action/unload")
    List<String[]> unloadBoxes(@RequestBody UnloadCommandDto unloadCommandDto);

    /**
     * Импортирует коробки из файла.
     *
     * @param importCommandDto параметры импорта
     * @return список всех коробок
     */
    @PostExchange("/api/v1/boxes/action/import")
    List<BoxDto> importBoxes(@RequestBody ImportCommandDto importCommandDto);
}
