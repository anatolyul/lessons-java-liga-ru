package ru.hofftech.console.packages.service;

import ru.hofftech.console.packages.model.Box;

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
     * @return список грузовиков с загруженными коробками
     */
    void loadBoxesInTrucks(List<Box> boxes, String trucksForms, Integer limitTrucks);
}
