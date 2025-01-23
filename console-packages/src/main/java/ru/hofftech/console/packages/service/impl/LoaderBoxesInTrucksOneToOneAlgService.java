package ru.hofftech.console.packages.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.repository.TruckRepository;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация сервиса для загрузки коробок в грузовики по алгоритму "одна посылка - один грузовик".
 */
@Service
@RequiredArgsConstructor
public class LoaderBoxesInTrucksOneToOneAlgService implements LoaderBoxesInTrucksService {
    private final TruckRepository truckRepository;

    /**
     * Загружает коробки в грузовики по алгоритму "одна посылка - один грузовик", с учетом ограничения на количество грузовиков.
     *
     * @param boxes       список коробок для загрузки
     * @param trucksForms формы грузовиков
     * @param limitTrucks максимальное количество грузовиков, которые могут быть использованы
     */
    @Override
    public void loadBoxesInTrucks(List<Box> boxes, String trucksForms, Integer limitTrucks) {
        List<Truck> trucks = new ArrayList<>();
        if (trucksForms == null || trucksForms.isBlank()) {
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
            trucks = truckRepository.createTrucksByForm(trucksForms);
            for (int i = 0; i < boxes.size() && i < trucks.size(); i++) {
                if (trucks.get(i) != null && trucks.get(i).canLoadBox(boxes.get(i))) {
                    trucks.get(i).placeBox(boxes.get(i), 0, 0);
                }
            }
        }
        truckRepository.setTrucks(trucks);
    }
}
