package ru.hofftech.consolepackages.service.impl;

import org.springframework.stereotype.Service;
import ru.hofftech.consolepackages.model.Box;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.model.TruckForm;
import ru.hofftech.consolepackages.service.LoaderBoxesInTrucksService;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация сервиса для равномерной загрузки коробок в грузовики.
 */
@Service
public class LoaderBoxesInTrucksUniformAlgService implements LoaderBoxesInTrucksService {

    /**
     * Загружает коробки в грузовики с учетом ограничения на количество грузовиков, используя равномерный алгоритм.
     *
     * @param boxes       список коробок для загрузки
     * @param trucksForms формы грузовиков
     * @param limitTrucks максимальное количество грузовиков, которые могут быть использованы
     */
    @Override
    public List<Truck> loadBoxesInTrucks(List<Box> boxes, TruckForm trucksForms, Integer limitTrucks) {
        List<Truck> trucks = new ArrayList<>();
        if (trucksForms.isNotValid()) {
            if (limitTrucks != null && limitTrucks > 0) {
                for (int i = 1; i <= limitTrucks; i++) {
                    Truck truck = new Truck("Truck " + i);
                    trucks.add(truck);
                }
            }
        } else {
            trucks = trucksForms.createTrucks();
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
