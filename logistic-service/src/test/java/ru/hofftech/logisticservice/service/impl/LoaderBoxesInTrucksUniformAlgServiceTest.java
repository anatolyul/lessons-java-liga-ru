package ru.hofftech.logisticservice.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.model.Truck;
import ru.hofftech.logisticservice.model.TruckForm;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoaderBoxesInTrucksUniformAlgServiceTest {

    private LoaderBoxesInTrucksUniformAlgService loaderBoxesInTrucksUniformAlgService;

    @BeforeEach
    void setUp() {
        loaderBoxesInTrucksUniformAlgService = new LoaderBoxesInTrucksUniformAlgService();
    }

    @Test
    @DisplayName("Тест: loadBoxesInTrucks должен корректно загружать коробки в грузовики без ограничения на количество грузовиков")
    void loadBoxesInTrucks_givenBoxesAndNoLimitTrucks_shouldLoadBoxesCorrectly() {
        List<BoxDto> boxes = Arrays.asList(
                new BoxDto("Box1", "xx\nxx", "1"),
                new BoxDto("Box2", "xx\nxx", "2"),
                new BoxDto("Box3", "xx\nxx", "3")
        );

        List<Truck> trucks = loaderBoxesInTrucksUniformAlgService.loadBoxesInTrucks(boxes, new TruckForm(null), null);
        assertEquals(0, trucks.size());
    }

    @Test
    @DisplayName("Тест: loadBoxesInTrucks должен корректно загружать коробки в грузовики с ограничением на количество грузовиков")
    void loadBoxesInTrucks_givenBoxesAndLimitTrucks_shouldLoadBoxesCorrectly() {
        List<BoxDto> boxes = Arrays.asList(
                new BoxDto("Box1", "xx\nxx", "1"),
                new BoxDto("Box2", "xx\nxx", "2"),
                new BoxDto("Box3", "xx\nxx", "3"),
                new BoxDto("Box4", "xx\nxx", "4"),
                new BoxDto("Box5", "xx\nxx", "5")
        );

        List<Truck> trucks = loaderBoxesInTrucksUniformAlgService.loadBoxesInTrucks(boxes, new TruckForm(null), 2);

        assertEquals(2, trucks.size());
        assertEquals(3, trucks.get(0).getBoxes().size());
        assertEquals(2, trucks.get(1).getBoxes().size());
    }

    @Test
    @DisplayName("Тест: loadBoxesInTrucks должен корректно загружать коробки в грузовики с заданной формой грузовиков")
    void loadBoxesInTrucks_givenBoxesAndTrucksForms_shouldLoadBoxesCorrectly() {
        List<BoxDto> boxes = Arrays.asList(
                new BoxDto("Box1", "xx\nxx", "1"),
                new BoxDto("Box2", "xx\nxx", "2"),
                new BoxDto("Box3", "xx\nxx", "3")
        );

        TruckForm trucksForms = new TruckForm("4x4\n3x3");

        List<Truck> trucks = loaderBoxesInTrucksUniformAlgService.loadBoxesInTrucks(boxes, trucksForms, null);

        assertEquals(2, trucks.size());
        assertEquals("Truck 4x4", trucks.get(0).getTruckName());
        assertEquals("Truck 3x3", trucks.get(1).getTruckName());
        assertEquals(2, trucks.get(0).getBoxes().size());
        assertEquals(1, trucks.get(1).getBoxes().size());
    }

    @Test
    @DisplayName("Тест: loadBoxesInTrucks должен корректно загружать коробки в грузовики с заданной формой грузовиков и ограничением на количество грузовиков")
    void loadBoxesInTrucks_givenBoxesAndTrucksFormsAndLimitTrucks_shouldLoadBoxesCorrectly() {
        List<BoxDto> boxes = Arrays.asList(
                new BoxDto("Box1", "xx\nxx", "1"),
                new BoxDto("Box2", "xx\nxx", "2"),
                new BoxDto("Box3", "xx\nxx", "3"),
                new BoxDto("Box4", "xx\nxx", "4"),
                new BoxDto("Box5", "xx\nxx", "5")
        );

        TruckForm trucksForms = new TruckForm("4x4\n4x4");

        List<Truck> trucks = loaderBoxesInTrucksUniformAlgService.loadBoxesInTrucks(boxes, trucksForms, 2);

        assertEquals(2, trucks.size());
        assertEquals("Truck 4x4", trucks.get(0).getTruckName());
        assertEquals("Truck 4x4", trucks.get(1).getTruckName());
        assertEquals(3, trucks.get(0).getBoxes().size());
        assertEquals(2, trucks.get(1).getBoxes().size());
    }
}
