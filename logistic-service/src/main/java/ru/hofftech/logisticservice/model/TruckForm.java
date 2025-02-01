package ru.hofftech.logisticservice.model;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class TruckForm {

    private static final int WIDTH_INDEX = 0;
    private static final int HEIGHT_INDEX = 1;
    String form;

    public TruckForm(String form) {
        this.form = form;
    }

    public static TruckForm fromString(String form) {
        return new TruckForm(form);
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
                        Integer.parseInt(sizes[WIDTH_INDEX]),
                        Integer.parseInt(sizes[HEIGHT_INDEX]));
                trucks.add(truck);
            }
        }
        return trucks;
    }
}
