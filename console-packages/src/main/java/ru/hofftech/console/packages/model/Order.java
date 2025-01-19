package ru.hofftech.console.packages.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class Order {
    private int orderId;
    private String userId;
    private LocalDate dateLoad;
    private LocalDate dateUnload;
    private List<Truck> trucks;

    public List<Box> getBoxes() {
        return getTrucks().stream()
                .filter(truck -> truck.getBoxes() != null)
                .flatMap(truck -> truck.getBoxes().stream())
                .toList();
    }
}
