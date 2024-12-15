package ru.hofftech.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Slf4j
public class Truck {
    private static final int TRUCK_WIDTH = 6;
    private static final int TRUCK_HEIGHT = 6;
    private boolean[][] cargoSpace;
    private String[][] cargoContent;

    public Truck() {
        cargoSpace = new boolean[TRUCK_HEIGHT][TRUCK_WIDTH];
        cargoContent = new String[TRUCK_HEIGHT][TRUCK_WIDTH];
    }

    public boolean canLoadBox(Box box) {
        for (int i = 0; i <= TRUCK_HEIGHT - box.height; i++) {
            for (int j = 0; j <= TRUCK_WIDTH - box.width; j++) {
                if (canPlaceBox(box, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void loadBox(Box box) {
        for (int i = 0; i <= TRUCK_HEIGHT - box.height; i++) {
            for (int j = 0; j <= TRUCK_WIDTH - box.width; j++) {
                if (canPlaceBox(box, i, j)) {
                    placeBox(box, i, j);
                    return;
                }
            }
        }
    }

    private boolean canPlaceBox(Box box, int startRow, int startCol) {
        for (int i = 0; i < box.height; i++) {
            for (int j = 0; j < box.width; j++) {
                if (cargoSpace[startRow + i][startCol + j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public void placeBox(Box box, int startRow, int startCol) {
        for (int i = 0; i < box.height; i++) {
            for (int j = 0; j < box.width; j++) {
                cargoSpace[startRow + i][startCol + j] = true;
                cargoContent[startRow + i][startCol + j] = box.content;
            }
        }
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
