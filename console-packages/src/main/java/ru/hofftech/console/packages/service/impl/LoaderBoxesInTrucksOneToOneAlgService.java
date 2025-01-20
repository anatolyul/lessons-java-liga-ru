package ru.hofftech.console.packages.service.impl;

import org.springframework.stereotype.Service;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация сервиса для загрузки коробок в грузовики по алгоритму "одна посылка - один грузовик".
 */
@Service
public class LoaderBoxesInTrucksOneToOneAlgService implements LoaderBoxesInTrucksService {

    /**
     * Загружает коробки в грузовики по алгоритму "одна посылка - один грузовик", с учетом ограничения на количество грузовиков.
     *
     * @param boxes       список коробок для загрузки
     * @param trucks      список грузовиков
     * @param limitTrucks максимальное количество грузовиков, которые могут быть использованы
     * @return список грузовиков с загруженными коробками
     */
    @Override
    public List<Truck> loadBoxesInTrucks(List<Box> boxes, List<Truck> trucks, Integer limitTrucks) {
        if (trucks.isEmpty()) {
            trucks = new ArrayList<>();
            int truckId = 1;

            for (Box box : boxes) {
                if (limitTrucks > 0 && truckId > limitTrucks) {
                    break;
                }
                Truck truck = new Truck("Truck " + truckId++);
                truck.placeBox(box, 0, 0);
                trucks.add(truck);
            }
        } else {
            for (int i = 0; i < boxes.size() && i < trucks.size(); i++) {
                if (trucks.get(i) != null && trucks.get(i).canLoadBox(boxes.get(i))) {
                    trucks.get(i).placeBox(boxes.get(i), 0, 0);
                }
            }
        }

        return trucks;
    }
}
