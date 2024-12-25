package ru.hofftech.console.packages.service.impl;

import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;

import java.util.ArrayList;
import java.util.List;

public class LoaderBoxesInTrucksSecondAlgService implements LoaderBoxesInTrucksService {
    @Override
    public List<Truck> loadBoxesInTrucks(List<Box> boxes) {
        List<Truck> trucks = new ArrayList<>();
        Truck currentTruck = new Truck();

        for (Box box : boxes) {
            if (!currentTruck.canLoadBox(box)) {
                trucks.add(currentTruck);
                currentTruck = new Truck();
            }
            currentTruck.loadBox(box);
        }

        trucks.add(currentTruck);
        return trucks;
    }
}
