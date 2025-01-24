package ru.hofftech.consolepackages.service.impl;

import org.springframework.stereotype.Service;
import ru.hofftech.consolepackages.model.Box;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.model.TruckForm;
import ru.hofftech.consolepackages.service.LoaderBoxesInTrucksService;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация сервиса для загрузки коробок в грузовики по алгоритму максимальной загрузки.
 */
@Service
public class LoaderBoxesInTrucksMaxAlgService implements LoaderBoxesInTrucksService {

    /**
     * Загружает коробки в грузовики по алгоритму максимальной загрузки, с учетом ограничения на количество грузовиков.
     *
     * @param boxes       список коробок для загрузки
     * @param trucksForms формы грузовиков
     * @param limitTrucks максимальное количество грузовиков, которые могут быть использованы
     */
    @Override
    public List<Truck> loadBoxesInTrucks(List<Box> boxes, TruckForm trucksForms, Integer limitTrucks) {
        List<Truck> trucks = new ArrayList<>();
        if (trucksForms.isNotValid()) {
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
            trucks = trucksForms.createTrucks();
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
