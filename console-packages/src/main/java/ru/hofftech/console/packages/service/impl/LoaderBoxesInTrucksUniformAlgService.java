package ru.hofftech.console.packages.service.impl;

import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;

import java.util.List;

@Slf4j
public class LoaderBoxesInTrucksUniformAlgService implements LoaderBoxesInTrucksService {
    @Override
    public List<Truck> loadBoxesInTrucks(List<Box> boxes, List<Truck> trucks, Integer limitTrucks) {
        if (trucks.isEmpty()) {
            for (int i = 1; i <= limitTrucks; i++) {
                Truck truck = new Truck("Truck " + i);
                trucks.add(truck);
            }
        }

        List<Box> sorterBoxes = boxes.stream()
                .sorted((s1, s2) ->
                        Integer.compare(
                                s2.getWidth(),
                                s1.getWidth()))
                .toList();

        for (Box box : sorterBoxes) {
            Truck currentTruck = getLessLoadedTruck(trucks);
            if (currentTruck != null && currentTruck.canLoadBox(box)) {
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

            for (String[] bb : truck.getCargoContent()) {
                for (String b : bb) {
                    if (b == null) {
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
