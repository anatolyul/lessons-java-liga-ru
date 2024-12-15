package ru.hofftech.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.model.Box;
import ru.hofftech.model.Truck;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class LoadingBoxesInTruckService {

    public List<Truck> simpleAlg(List<Box> boxes) {
        List<Truck> trucks = new ArrayList<>();

        for (Box box : boxes) {
            Truck truck = new Truck();
            truck.placeBox(box, 0, 0);
            trucks.add(truck);
        }

        return trucks;
    }

    public List<Truck> complexAlg(List<Box> boxes) {
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

    public void report(List<Truck> trucks) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        for (int i = 0; i < trucks.size(); i++) {
            sb.append("Truck ")
                .append(i + 1)
                .append(":\n")
                .append(trucks.get(i).printCargo())
                .append("\n");
        }

        log.info(sb.toString());
    }
}
