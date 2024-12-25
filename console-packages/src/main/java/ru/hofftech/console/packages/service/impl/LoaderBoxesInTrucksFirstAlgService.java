package ru.hofftech.console.packages.service.impl;

import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;

import java.util.ArrayList;
import java.util.List;

public class LoaderBoxesInTrucksFirstAlgService implements LoaderBoxesInTrucksService {
    @Override
    public List<Truck> loadBoxesInTrucks(List<Box> boxes) {
        List<Truck> trucks = new ArrayList<>();

        for (Box box : boxes) {
            Truck truck = new Truck();
            truck.placeBox(box, 0, 0);
            trucks.add(truck);
        }

        return trucks;
    }
}
