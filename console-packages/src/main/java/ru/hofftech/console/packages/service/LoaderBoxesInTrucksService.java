package ru.hofftech.console.packages.service;

import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;

import java.util.List;

public interface LoaderBoxesInTrucksService {
    List<Truck> loadBoxesInTrucks(List<Box> boxes);
}
