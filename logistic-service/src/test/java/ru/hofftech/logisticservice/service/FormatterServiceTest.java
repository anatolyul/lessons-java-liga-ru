package ru.hofftech.logisticservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.model.Truck;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FormatterServiceTest {

    private FormatterService formatterService;

    @BeforeEach
    void setUp() {
        formatterService = new FormatterService();
    }

    @Test
    @DisplayName("Тест: trucksToString должен корректно преобразовывать список грузовиков в строку")
    void trucksToString_shouldConvertTrucksToCorrectString() {
        List<Truck> trucks = List.of(
                new Truck("Truck1", List.of(new BoxDto("Box1", "xx\nxx", "1"), new BoxDto("Box2", "xx\nxx", "2"))),
                new Truck("Truck2", List.of(new BoxDto("Box3", "xx\nxx", "3")))
        );

        String result = formatterService.trucksToString(trucks);

        assertTrue(result.contains("Truck1"));
        assertTrue(result.contains("Truck2"));
    }

    @Test
    @DisplayName("Тест: trucksToJson должен корректно преобразовывать список грузовиков в JSON строку")
    void trucksToJson_shouldConvertTrucksToCorrectJsonString() {
        List<Truck> trucks = List.of(
                new Truck("Truck1", List.of(
                        new BoxDto("Box1", "xx\nxx", "1"),
                        new BoxDto("Box2", "xx\nxx", "2"))),
                new Truck("Truck2", List.of(
                        new BoxDto("Box3", "xx\nxx", "3")))
        );

        String result = formatterService.trucksToJson(trucks);

        assertTrue(result.contains("Truck1"));
        assertTrue(result.contains("Truck2"));
        assertTrue(result.contains("Box1"));
        assertTrue(result.contains("Box2"));
        assertTrue(result.contains("Box3"));
    }

    @Test
    @DisplayName("Тест: boxesToString должен корректно преобразовывать список коробок в строку")
    void boxesToString_shouldConvertBoxesToCorrectString() {
        List<BoxDto> boxes = List.of(
                new BoxDto("Box1", "xx\nxx", "1"),
                new BoxDto("Box2", "xx\nxx", "2"),
                new BoxDto("Box3", "xx\nxx", "3")
        );

        String result = formatterService.boxesToString(boxes);

        assertTrue(result.contains("11\n11"));
        assertTrue(result.contains("22\n22"));
        assertTrue(result.contains("33\n33"));
    }

    @Test
    @DisplayName("Тест: stringToLocalDate должен корректно преобразовывать строку в LocalDate")
    void stringToLocalDate_shouldConvertStringToLocalDate() {
        String dateString = "01.01.2023";
        LocalDate expectedDate = LocalDate.of(2023, 1, 1);

        LocalDate result = formatterService.stringToLocalDate(dateString);

        assertEquals(expectedDate, result);
    }
}
