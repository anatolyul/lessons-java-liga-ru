package ru.hofftech.consolepackages.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.model.Box;
import ru.hofftech.consolepackages.model.Truck;

import java.time.LocalDate;
import java.util.Arrays;
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
        List<Truck> trucks = Arrays.asList(
                new Truck("Truck1", Arrays.asList(new Box("Box1", "xx\nxx", "1"), new Box("Box2", "xx\nxx", "2"))),
                new Truck("Truck2", Arrays.asList(new Box("Box3", "xx\nxx", "3")))
        );

        String result = formatterService.trucksToString(trucks);

        assertTrue(result.contains("Truck1"));
        assertTrue(result.contains("Truck2"));
    }

    @Test
    @DisplayName("Тест: trucksToJson должен корректно преобразовывать список грузовиков в JSON строку")
    void trucksToJson_shouldConvertTrucksToCorrectJsonString() {
        List<Truck> trucks = Arrays.asList(
                new Truck("Truck1", Arrays.asList(new Box("Box1", "xx\nxx", "1"), new Box("Box2", "xx\nxx", "2"))),
                new Truck("Truck2", Arrays.asList(new Box("Box3", "xx\nxx", "3")))
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
        List<Box> boxes = Arrays.asList(
                new Box("Box1", "xx\nxx", "1"),
                new Box("Box2", "xx\nxx", "2"),
                new Box("Box3", "xx\nxx", "3")
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

    @Test
    @DisplayName("Тест: formatOrderOperationToBillingString должен корректно форматировать строку для биллинга")
    void formatOrderOperationToBillingString_shouldFormatStringCorrectly() {
        LocalDate date = LocalDate.of(2023, 1, 1);
        int truckCount = 2;
        int sizeBox = 10;
        double cost = 100.0;
        String operation = "Загрузка";
        LocalDate periodFrom = LocalDate.of(2022, 12, 1);
        LocalDate periodTo = LocalDate.of(2023, 2, 1);

        String result = formatterService.formatOrderOperationToBillingString(date, truckCount, sizeBox, cost, operation, periodFrom, periodTo);

        assertEquals("2023-01-01; Загрузка; 2 машин; 10 посылок; 1000,00 рублей", result);
    }

    @Test
    @DisplayName("Тест: formatOrderOperationToBillingString должен возвращать пустую строку, если дата вне периода")
    void formatOrderOperationToBillingString_shouldReturnEmptyStringIfDateOutOfPeriod() {
        LocalDate date = LocalDate.of(2023, 3, 1);
        int truckCount = 2;
        int sizeBox = 10;
        double cost = 100.0;
        String operation = "Загрузка";
        LocalDate periodFrom = LocalDate.of(2022, 12, 1);
        LocalDate periodTo = LocalDate.of(2023, 2, 1);

        String result = formatterService.formatOrderOperationToBillingString(date, truckCount, sizeBox, cost, operation, periodFrom, periodTo);

        assertEquals("", result);
    }
}
