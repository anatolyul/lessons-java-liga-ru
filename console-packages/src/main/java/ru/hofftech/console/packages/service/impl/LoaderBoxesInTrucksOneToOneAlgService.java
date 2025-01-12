package ru.hofftech.console.packages.service.impl;

import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LoaderBoxesInTrucksOneToOneAlgService implements LoaderBoxesInTrucksService {
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
            for (int i = 0; i < boxes.size(); i++) {
                if (trucks.get(i) != null) {
                    trucks.get(i).placeBox(boxes.get(i), 0, 0);
                }
            }
        }

        return trucks;
    }
}
