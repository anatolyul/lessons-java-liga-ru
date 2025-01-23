package ru.hofftech.consolepackages.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Модель грузовика, который может содержать коробки.
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Truck {

    private int truckHeight = 6;
    private int truckWidth = 6;

    @JsonProperty("truck_type")
    private String truckName;

    @JsonProperty("parcels")
    private List<Box> boxes;

    @JsonIgnore
    private String[][] cargoContent;

    /**
     * Конструктор с именем грузовика и списком коробок.
     *
     * @param truckName имя грузовика
     * @param boxes     список коробок
     */
    public Truck(String truckName, List<Box> boxes) {
        this.truckName = truckName;
        this.boxes = boxes;
        cargoContent = new String[truckHeight][truckWidth];
    }

    /**
     * Конструктор с именем грузовика.
     *
     * @param truckName имя грузовика
     */
    public Truck(String truckName) {
        this.truckName = truckName;
        cargoContent = new String[truckHeight][truckWidth];
    }

    /**
     * Конструктор с именем грузовика, высотой и шириной.
     *
     * @param truckName   имя грузовика
     * @param truckHeight высота грузовика
     * @param truckWidth  ширина грузовика
     */
    public Truck(String truckName, int truckHeight, int truckWidth) {
        this.truckName = truckName;
        this.truckHeight = truckHeight;
        this.truckWidth = truckWidth;
        cargoContent = new String[truckHeight][truckWidth];
    }

    /**
     * Добавляет коробку в грузовик.
     *
     * @param box коробка для добавления
     */
    private void addBox(Box box) {
        List<Box> boxList = this.getBoxes() != null ? this.getBoxes() : new ArrayList<>();
        boxList.add(box);
        this.setBoxes(boxList);
    }

    /**
     * Проверяет, можно ли загрузить коробку в грузовик.
     *
     * @param box коробка для проверки
     * @return true, если коробку можно загрузить, иначе false
     */
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

    /**
     * Загружает коробку в грузовик.
     *
     * @param box коробка для загрузки
     */
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

    /**
     * Проверяет, можно ли разместить коробку в указанной позиции.
     *
     * @param box         коробка для проверки
     * @param startHeight начальная высота для размещения
     * @param startWidth  начальная ширина для размещения
     * @return true, если коробку можно разместить, иначе false
     */
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

    /**
     * Размещает коробку в указанной позиции.
     *
     * @param box         коробка для размещения
     * @param startHeight начальная высота для размещения
     * @param startWidth  начальная ширина для размещения
     */
    public void placeBox(Box box, int startHeight, int startWidth) {
        for (int i = 0; i < box.getHeight(); i++) {
            for (int j = 0; j < box.getWidth(); j++) {
                cargoContent[startHeight + i][startWidth + j] = box.getSymbol();
            }
        }
        box.setStartPosition(startHeight, startWidth);
        addBox(box);
    }

    /**
     * Возвращает строку, представляющую содержимое грузовика.
     *
     * @return строка, представляющая содержимое грузовика
     */
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
