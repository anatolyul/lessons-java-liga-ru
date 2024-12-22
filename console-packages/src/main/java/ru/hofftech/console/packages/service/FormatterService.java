package ru.hofftech.console.packages.service;

import ru.hofftech.console.packages.model.Truck;
import java.util.List;

public class FormatterService {
    public static String TrucksToString(List<Truck> trucks) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        for (int i = 0; i < trucks.size(); i++) {
            sb.append("Truck ")
                    .append(i + 1)
                    .append(":\n")
                    .append(trucks.get(i).printCargo())
                    .append("\n");
        }

        return sb.toString();
    }
}
