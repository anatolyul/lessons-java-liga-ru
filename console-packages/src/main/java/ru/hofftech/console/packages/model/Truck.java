package ru.hofftech.console.packages.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Truck {
    @JsonIgnore
    private int truckHeight = 6;

    @JsonIgnore
    private int truckWidth = 6;

    @JsonProperty("truck_type")
    private String truckName;

    @JsonProperty("parcels")
    private List<Box> boxes;

    @JsonIgnore
    private String[][] cargoContent;

    private void addBox(Box box) {
        List<Box> boxes = this.getBoxes() != null ? this.getBoxes() : new ArrayList<>();
        boxes.add(box);
        this.setBoxes(boxes);
    }

    public Truck() {
        cargoContent = new String[truckHeight][truckWidth];
    }

    public Truck(String truckName) {
        this.truckName = truckName;
        cargoContent = new String[truckHeight][truckWidth];
    }

    public Truck(String truckName, int truckHeight, int truckWidth) {
        this.truckName = truckName;
        this.truckHeight = truckHeight;
        this.truckWidth = truckWidth;
        cargoContent = new String[truckHeight][truckWidth];
    }

    public boolean canLoadBox(Box box) {
        for (int i = 0; i <= truckHeight - box.getHeight(); i++) {
            for (int j = 0; j <= truckWidth - box.getWidth(); j++) {
                if (canPlaceBox(box, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void loadBox(Box box) {
        for (int i = 0; i <= truckHeight - box.getHeight(); i++) {
            for (int j = 0; j <= truckWidth - box.getWidth(); j++) {
                if (canPlaceBox(box, i, j)) {
                    placeBox(box, i, j);
                    return;
                }
            }
        }
    }

    private boolean canPlaceBox(Box box, int startHeight, int startWidth) {
        for (int i = 0; i < box.getHeight(); i++) {
            for (int j = 0; j < box.getWidth(); j++) {
                if (cargoContent[startHeight + i][startWidth + j] != null) {
                    return false;
                }
            }
        }

        return true;
    }

    public void placeBox(Box box, int startHeight, int startWidth) {
        for (int i = 0; i < box.getHeight(); i++) {
            for (int j = 0; j < box.getWidth(); j++) {
                cargoContent[startHeight + i][startWidth + j] = box.getSymbol();
            }
        }
        box.TruckPosition(startHeight, startWidth);
        addBox(box);
    }

    public String printCargo() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < truckHeight; i++) {
            sb.append("+");
            for (int j = 0; j < truckWidth; j++) {
                // переворачиваем матрицу
                sb.append(cargoContent[truckHeight - 1 - i][j] != null
                        ? cargoContent[truckHeight - 1 - i][j]
                        : " ");
            }
            sb.append("+\n");
        }

        sb.append("+".repeat(truckWidth + 2));
        sb.append("\n");

        return sb.toString();
    }
}
