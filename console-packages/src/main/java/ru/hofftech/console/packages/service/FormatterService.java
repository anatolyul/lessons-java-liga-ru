package ru.hofftech.console.packages.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;

import java.util.List;

/**
 * Сервис для форматирования данных о грузовиках и коробках.
 */
public class FormatterService {

    /**
     * Преобразует список грузовиков в строку.
     *
     * @param trucks список грузовиков
     * @return строка, представляющая список грузовиков
     */
    public String TrucksToString(List<Truck> trucks) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        for (Truck truck : trucks) {
            sb.append(truck.getTruckName())
                    .append(":\n")
                    .append(truck.printCargo())
                    .append("\n");
        }

        return sb.toString();
    }

    /**
     * Преобразует список грузовиков в JSON строку.
     *
     * @param trucks список грузовиков
     * @return JSON строка, представляющая список грузовиков
     */
    public String TrucksToJson(List<Truck> trucks) {
        String result;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            result = objectMapper.writeValueAsString(trucks);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * Преобразует список коробок в строку.
     *
     * @param boxes список коробок
     * @return строка, представляющая список коробок
     */
    public String BoxesToString(List<Box> boxes) {
        StringBuilder sb = new StringBuilder();

        for (Box box : boxes) {
            for (int j = 0; j < box.getHeight(); j++) {
                sb.append(box.getSymbol().repeat(box.getWidth())).append("\n");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}