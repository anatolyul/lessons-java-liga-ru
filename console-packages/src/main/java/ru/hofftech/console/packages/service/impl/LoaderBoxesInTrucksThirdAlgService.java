package ru.hofftech.console.packages.service.impl;

import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LoaderBoxesInTrucksThirdAlgService implements LoaderBoxesInTrucksService {
    @Override
    public List<Truck> loadBoxesInTrucks(List<Box> boxes, int limitTrucks) {
        List<Truck> trucks = new ArrayList<>();

        for (int i = 1; i <= limitTrucks; i++) {
            Truck truck = new Truck();
            truck.setTruckName("Truck " + i);
            trucks.add(truck);
        }

        List<Box> sorterBoxes = boxes.stream()
                .sorted((s1, s2) ->
                        Integer.compare(
                                s2.getWidth(),
                                s1.getWidth()))
                .toList();

        for (Box box : sorterBoxes) {
            Truck currentTruck = getLessLoadedTruck(trucks);
            if (!currentTruck.canLoadBox(box)) {
                log.error("Груз превышает кол-во предоставленных машин");
                return new ArrayList<>();
            } else {
                currentTruck.loadBox(box);
            }
        }

        return trucks;
    }

    private Truck getLessLoadedTruck(List<Truck> trucks) {
        int maxCount = 0;
        Truck truckResult = null;

        for (Truck truck : trucks) {
            int count = 0;

            for (boolean[] bb : truck.getCargoSpace()) {
                for (boolean b : bb) {
                    if (!b) {
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
