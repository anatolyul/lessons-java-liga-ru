package ru.hofftech.console.packages.service.impl;

import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;

import java.util.List;

/**
 * Реализация сервиса для загрузки коробок в грузовики по алгоритму максимальной загрузки.
 */
public class LoaderBoxesInTrucksMaxAlgService implements LoaderBoxesInTrucksService {

    /**
     * Загружает коробки в грузовики по алгоритму максимальной загрузки, с учетом ограничения на количество грузовиков.
     *
     * @param boxes       список коробок для загрузки
     * @param trucks      список грузовиков
     * @param limitTrucks максимальное количество грузовиков, которые могут быть использованы
     * @return список грузовиков с загруженными коробками
     */
    @Override
    public List<Truck> loadBoxesInTrucks(List<Box> boxes, List<Truck> trucks, Integer limitTrucks) {
        if (trucks.isEmpty()) {
            int truckId = 1;
            Truck truck = new Truck("Truck " + truckId);

            for (Box box : boxes) {
                if (!truck.canLoadBox(box)) {
                    if (limitTrucks > 0 && truckId == limitTrucks) {
                        break;
                    }
                    trucks.add(truck);
                    truck = new Truck("Truck " + truckId++);
                }
                truck.loadBox(box);
            }
            trucks.add(truck);
        } else {
            for (Box box : boxes) {
                for (Truck truck : trucks) {
                    if (truck.canLoadBox(box)) {
                        truck.loadBox(box);
                        break;
                    }
                }
            }
        }

        return trucks;
    }
}
