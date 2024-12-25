package ru.hofftech.console.packages.service.impl;

import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;

import java.util.ArrayList;
import java.util.List;

public class LoaderBoxesInTrucksFirstAlgService implements LoaderBoxesInTrucksService {
    @Override
    public List<Truck> loadBoxesInTrucks(List<Box> boxes, int limitTrucks) {
        List<Truck> trucks = new ArrayList<>();
        int truckId = 1;

        for (Box box : boxes) {
            Truck truck = new Truck();
            truck.setTruckName("Truck " + truckId++);
            truck.placeBox(box, 0, 0);
            trucks.add(truck);
        }

        if (trucks.size() > limitTrucks) {
            throw new IllegalArgumentException("Груз превышает кол-во предоставленных машин");
        }

        return trucks;
    }
}
