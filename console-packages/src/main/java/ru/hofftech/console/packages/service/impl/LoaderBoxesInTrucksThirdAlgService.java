package ru.hofftech.console.packages.service.impl;

import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;

import java.util.ArrayList;
import java.util.List;

public class LoaderBoxesInTrucksThirdAlgService implements LoaderBoxesInTrucksService {
    @Override
    public List<Truck> loadBoxesInTrucks(List<Box> boxes, int limitTrucks) {
        List<Truck> trucks = new ArrayList<>();
//TODO реализация алгоритма баланса
        return trucks;
    }
}
