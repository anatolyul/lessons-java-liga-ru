package ru.hofftech.consolepackages.model;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class TruckForm {
    String form;

    public TruckForm(String form) {
        this.form = form;
    }

    public boolean isNotValid() {
        return form == null || form.isBlank();
    }

    public List<Truck> createTrucks() {
        List<Truck> trucks = new ArrayList<>();
        if (form != null && !form.isEmpty()) {
            String[] truckDimensions = form.replace("n", "\n")
                    .replace("\\n", "\n").split("\n");
            for (String dimension : truckDimensions) {
                String[] sizes = dimension.split("x");
                Truck truck = new Truck("Truck " + dimension,
                        Integer.parseInt(sizes[0]),
                        Integer.parseInt(sizes[1]));
                trucks.add(truck);
            }
        }
        return trucks;
    }
}
