package ru.hofftech.consolepackages.service.impl;

import org.springframework.stereotype.Service;
import ru.hofftech.consolepackages.model.Box;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.model.TruckForm;
import ru.hofftech.consolepackages.service.LoaderBoxesInTrucksService;

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
     * @param trucksForms формы грузовиков
     * @param limitTrucks максимальное количество грузовиков, которые могут быть использованы
     */
    @Override
    public List<Truck> loadBoxesInTrucks(List<Box> boxes, TruckForm trucksForms, Integer limitTrucks) {
        List<Truck> trucks = new ArrayList<>();
        if (trucksForms.isNotValid()) {
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
            trucks = trucksForms.createTrucks();
            for (int i = 0; i < boxes.size() && i < trucks.size(); i++) {
                if (trucks.get(i) != null && trucks.get(i).canLoadBox(boxes.get(i))) {
                    trucks.get(i).placeBox(boxes.get(i), 0, 0);
                }
            }
        }

        return trucks;
    }
}
