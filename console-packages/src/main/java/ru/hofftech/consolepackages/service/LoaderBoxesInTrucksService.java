package ru.hofftech.consolepackages.service;

import ru.hofftech.consolepackages.model.Box;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.model.TruckForm;

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
    List<Truck> loadBoxesInTrucks(List<Box> boxes, TruckForm trucksForms, Integer limitTrucks);
}
