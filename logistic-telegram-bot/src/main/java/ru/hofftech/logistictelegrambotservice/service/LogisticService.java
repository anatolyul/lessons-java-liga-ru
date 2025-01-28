package ru.hofftech.logistictelegrambotservice.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;
import ru.hofftech.logistictelegrambotservice.dto.BoxDto;
import ru.hofftech.logistictelegrambotservice.dto.BoxForUpdateDto;
import ru.hofftech.logistictelegrambotservice.dto.ImportParamDto;
import ru.hofftech.logistictelegrambotservice.dto.LoadParamDto;
import ru.hofftech.logistictelegrambotservice.dto.OrderDto;
import ru.hofftech.logistictelegrambotservice.dto.TruckDto;
import ru.hofftech.logistictelegrambotservice.dto.UnloadParamDto;

import java.time.LocalDate;
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
    @GetExchange("/api/v1/box")
    List<BoxDto> findAllBoxes();

    /**
     * Получает коробку по имени.
     *
     * @param name имя коробки
     * @return коробка с указанным именем
     */
    @GetExchange("/api/v1/box/{name}")
    BoxDto findBoxByName(@PathVariable String name);

    /**
     * Создает новую коробку.
     *
     * @param box данные новой коробки
     * @return созданная коробка
     */
    @PostExchange("/api/v1/box")
    BoxDto createBox(@RequestBody BoxDto box);

    /**
     * Обновляет существующую коробку.
     *
     * @param box  новые данные коробки
     * @return обновленная коробка
     */
    @PutExchange("/api/v1/box")
    BoxDto updateBox(@RequestBody BoxForUpdateDto box);

    /**
     * Удаляет коробку по имени.
     *
     * @param name имя коробки
     * @return результат удаления
     */
    @DeleteExchange("/api/v1/box/{name}")
    Boolean deleteBoxByName(@PathVariable String name);

    /**
     * Загружает коробки в грузовики.
     *
     * @param loadParamDto параметры загрузки
     * @return список всех коробок
     */
    @PostExchange("/api/v1/box/action/load")
    List<TruckDto> loadBoxes(@RequestBody LoadParamDto loadParamDto);

    /**
     * Разгружает грузовики.
     *
     * @param unloadParamDto параметры разгрузки
     * @return список всех коробок
     */
    @PostExchange("/api/v1/box/action/unload")
    List<String[]> unloadBoxes(@RequestBody UnloadParamDto unloadParamDto);

    /**
     * Импортирует коробки из файла.
     *
     * @param importParamDto параметры импорта
     * @return список всех коробок
     */
    @PostExchange("/api/v1/box/action/import")
    List<BoxDto> importBoxes(@RequestBody ImportParamDto importParamDto);

    /**
     * Получает список всех заказов.
     *
     * @return список всех заказов
     */
    @GetExchange("/api/v1/billing")
    List<OrderDto> findAllOrders();

    /**
     * Получает заказы по имени клиента и периоду.
     *
     * @param clientName имя клиента
     * @param startDate  начальная дата периода
     * @param endDate    конечная дата периода
     * @return список заказов, соответствующих критериям
     */
    @GetExchange("/api/v1/billing/find")
    List<OrderDto> findOrdersByNameWithPeriod(@RequestParam String clientName,
                                              @RequestParam LocalDate startDate,
                                              @RequestParam LocalDate endDate);
}
