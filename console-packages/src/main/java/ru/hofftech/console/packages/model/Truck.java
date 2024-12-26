package ru.hofftech.console.packages.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Truck {
    private static final int TRUCK_WIDTH = 6;
    private static final int TRUCK_HEIGHT = 6;
    private String truckName;
    private List<Box> boxes;

    @JsonIgnore
    private boolean[][] cargoSpace;

    @JsonIgnore
    private String[][] cargoContent;

    private void addBox(Box box) {
        List<Box> boxes = this.getBoxes() != null ? this.getBoxes() : new ArrayList<>();
        boxes.add(box);
        this.setBoxes(boxes);
    }

    public Truck() {
        cargoSpace = new boolean[TRUCK_HEIGHT][TRUCK_WIDTH];
        cargoContent = new String[TRUCK_HEIGHT][TRUCK_WIDTH];
    }

    public Truck(String truckName) {
        this.truckName = truckName;
        cargoSpace = new boolean[TRUCK_HEIGHT][TRUCK_WIDTH];
        cargoContent = new String[TRUCK_HEIGHT][TRUCK_WIDTH];
    }

    public boolean canLoadBox(Box box) {
        for (int i = 0; i <= TRUCK_HEIGHT - box.getHeight(); i++) {
            for (int j = 0; j <= TRUCK_WIDTH - box.getWidth(); j++) {
                if (canPlaceBox(box, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void loadBox(Box box) {
        for (int i = 0; i <= TRUCK_HEIGHT - box.getHeight(); i++) {
            for (int j = 0; j <= TRUCK_WIDTH - box.getWidth(); j++) {
                if (canPlaceBox(box, i, j)) {
                    placeBox(box, i, j);
                    return;
                }
            }
        }
    }

    private boolean canPlaceBox(Box box, int startRow, int startCol) {
        for (int i = 0; i < box.getHeight(); i++) {
            for (int j = 0; j < box.getWidth(); j++) {
                if (cargoSpace[startRow + i][startCol + j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public void placeBox(Box box, int startRow, int startCol) {
        for (int i = 0; i < box.getHeight(); i++) {
            for (int j = 0; j < box.getWidth(); j++) {
                cargoSpace[startRow + i][startCol + j] = true;
                cargoContent[startRow + i][startCol + j] = box.getContent();
            }
        }
        box.TruckPosition(startRow, startCol);
        addBox(box);
    }

    public String printCargo() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < TRUCK_HEIGHT; i++) {
            sb.append("+");
            for (int j = 0; j < TRUCK_WIDTH; j++) {
                // переворачиваем матрицу
                sb.append(cargoContent[TRUCK_HEIGHT - 1 - i][j] != null ? cargoContent[TRUCK_HEIGHT - 1 - i][j] : " ");
            }
            sb.append("+\n");
        }

        sb.append("+".repeat(TRUCK_WIDTH + 2));
        sb.append("\n");

        return sb.toString();
    }
}
