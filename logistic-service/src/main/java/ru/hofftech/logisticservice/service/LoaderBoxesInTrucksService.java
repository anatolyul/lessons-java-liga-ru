package ru.hofftech.logisticservice.service;

import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.model.Truck;
import ru.hofftech.logisticservice.model.TruckForm;

import java.util.List;

/**
 * Интерфейс для загрузки коробок в грузовики.
 */

public interface LoaderBoxesInTrucksService {

    /**
     * Метод для загрузки коробок в грузовики с учетом ограничения на количество грузовиков.
     *
     * @param boxes       список коробок для загрузки
     * @param trucksForms формы грузовиков
     * @param limitTrucks максимальное количество грузовиков, которые могут быть использованы
     */
    List<Truck> loadBoxesInTrucks(List<BoxDto> boxes, TruckForm trucksForms, Integer limitTrucks);
}
