package ru.hofftech.logistictelegrambotservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Модель грузовика, который может содержать коробки.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TruckDto {

    private int truckHeight = 6;
    private int truckWidth = 6;
    @JsonProperty("truck_type")
    private String truckName;
    @JsonProperty("parcels")
    private List<BoxDto> boxes;
    private String[][] cargoContent;

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

    @Override
    public String toString() {
        return getTruckName() + ":\n" + printCargo() + "\n";
    }
}
