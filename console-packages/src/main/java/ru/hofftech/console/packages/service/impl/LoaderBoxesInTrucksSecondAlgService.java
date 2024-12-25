package ru.hofftech.console.packages.service.impl;

import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LoaderBoxesInTrucksSecondAlgService implements LoaderBoxesInTrucksService {
    @Override
    public List<Truck> loadBoxesInTrucks(List<Box> boxes, int limitTrucks) {
        List<Truck> trucks = new ArrayList<>();
        Truck currentTruck = new Truck();
        int truckId = 1;
        currentTruck.setTruckName("Truck " + truckId++);

        for (Box box : boxes) {
            if (!currentTruck.canLoadBox(box)) {
                trucks.add(currentTruck);
                currentTruck = new Truck();
                currentTruck.setTruckName("Truck " + truckId++);
            }
            currentTruck.loadBox(box);
        }

        trucks.add(currentTruck);

        if (trucks.size() > limitTrucks) {
            log.error("Груз превышает кол-во предоставленных машин");
            return new ArrayList<>();
        }

        return trucks;
    }
}
