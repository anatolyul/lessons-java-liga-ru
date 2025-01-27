package ru.hofftech.logisticservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.dto.OrderDto;
import ru.hofftech.logisticservice.model.Truck;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Сервис для форматирования данных о грузовиках и коробках.
 */
@Service
public class FormatterService {

    /**
     * Преобразует список грузовиков в строку.
     *
     * @param trucks список грузовиков
     * @return строка, представляющая список грузовиков
     */
    public String trucksToString(List<Truck> trucks) {
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
    public String trucksToJson(List<Truck> trucks) {
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
    public String boxesToString(List<BoxDto> boxes) {
        StringBuilder sb = new StringBuilder();

        for (BoxDto box : boxes) {
            for (int j = 0; j < box.getHeight(); j++) {
                sb.append(box.getSymbol().repeat(box.getWidth())).append("\n");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public String formatBoxesToString(List<BoxDto> boxes) {
        StringBuilder result = new StringBuilder();
        result.append("Список посылок:\n");

        for (BoxDto box : boxes) {
            result.append(String.format("""
                    
                    Посылка:
                    name: %s
                    form:
                    %s
                    """, box.getName(), box.getForm()));
        }

        return result.toString();
    }

    public LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }

    public String formatOrderOperationToBillingString(OrderDto orderDto) {
        return String.format("%s; %s; %d машин; %d посылок; %.2f рублей",
                orderDto.getDate(),
                orderDto.getType().getCode(),
                orderDto.getTruckCount(),
                orderDto.getBoxCount(),
                orderDto.getAmount());
    }
}