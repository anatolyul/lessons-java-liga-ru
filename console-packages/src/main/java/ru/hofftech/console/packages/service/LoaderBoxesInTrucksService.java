package ru.hofftech.console.packages.service;

import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;

import java.util.List;

/**
 * Интерфейс для загрузки коробок в грузовики.
 */

public interface LoaderBoxesInTrucksService {

    /**
     * Метод для загрузки коробок в грузовики с учетом ограничения на количество грузовиков.
     *
     * @param boxes       список коробок для загрузки
     * @param trucks      список грузовиков
     * @param limitTrucks максимальное количество грузовиков, которые могут быть использованы
     * @return список грузовиков с загруженными коробками
     */
    List<Truck> loadBoxesInTrucks(List<Box> boxes, List<Truck> trucks, Integer limitTrucks);
}
