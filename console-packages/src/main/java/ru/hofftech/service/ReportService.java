package ru.hofftech.service;

import lombok.extern.slf4j.Slf4j;
import ru.hofftech.model.Truck;

import java.util.List;

@Slf4j
public class ReportService {
    public static void report(List<Truck> trucks) {
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
