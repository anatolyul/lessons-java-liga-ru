package ru.hofftech.console.packages.service.impl;

import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;

import java.util.List;

/**
 * Реализация сервиса для равномерной загрузки коробок в грузовики.
 */
@Slf4j
public class LoaderBoxesInTrucksUniformAlgService implements LoaderBoxesInTrucksService {

    /**
     * Загружает коробки в грузовики с учетом ограничения на количество грузовиков, используя равномерный алгоритм.
     *
     * @param boxes       список коробок для загрузки
     * @param trucks      список грузовиков
     * @param limitTrucks максимальное количество грузовиков, которые могут быть использованы
     * @return список грузовиков с загруженными коробками
     */
    @Override
    public List<Truck> loadBoxesInTrucks(List<Box> boxes, List<Truck> trucks, Integer limitTrucks) {
        if (trucks.isEmpty()) {
            for (int i = 1; i <= limitTrucks; i++) {
                Truck truck = new Truck("Truck " + i);
                trucks.add(truck);
            }
        }

        List<Box> sortedBoxes = boxes.stream()
                .sorted((s1, s2) -> Integer.compare(s2.getWidth(), s1.getWidth()))
                .toList();

        for (Box box : sortedBoxes) {
            Truck currentTruck = getLessLoadedTruck(trucks);
            if (currentTruck != null && currentTruck.canLoadBox(box)) {
                currentTruck.loadBox(box);
            }
        }

        return trucks;
    }

    /**
     * Находит грузовик с наименьшей загрузкой.
     *
     * @param trucks список грузовиков
     * @return грузовик с наименьшей загрузкой
     */
    private Truck getLessLoadedTruck(List<Truck> trucks) {
        int maxCount = 0;
        Truck truckResult = null;

        for (Truck truck : trucks) {
            int count = 0;

            for (String[] bb : truck.getCargoContent()) {
                for (String b : bb) {
                    if (b == null) {
                        count++;
                    }
                }
            }

            if (count > maxCount) {
                maxCount = count;
                truckResult = truck;
            }
        }

        return truckResult;
    }
}
