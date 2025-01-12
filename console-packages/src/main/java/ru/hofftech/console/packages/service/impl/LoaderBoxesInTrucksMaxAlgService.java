package ru.hofftech.console.packages.service.impl;

import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;

import java.util.List;

@Slf4j
public class LoaderBoxesInTrucksMaxAlgService implements LoaderBoxesInTrucksService {
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
