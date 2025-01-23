package ru.hofftech.consolepackages.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.model.Box;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.repository.TruckRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoaderBoxesInTrucksMaxAlgServiceTest {

    private LoaderBoxesInTrucksMaxAlgService loaderBoxesInTrucksMaxAlgService;
    private TruckRepository truckRepository;

    @BeforeEach
    void setUp() {
        truckRepository = new TruckRepository();
        loaderBoxesInTrucksMaxAlgService = new LoaderBoxesInTrucksMaxAlgService(truckRepository);
    }

    @Test
    @DisplayName("Тест: loadBoxesInTrucks должен корректно загружать коробки в грузовики без ограничения на количество грузовиков")
    void loadBoxesInTrucks_givenBoxesAndNoLimitTrucks_shouldLoadBoxesCorrectly() {
        List<Box> boxes = Arrays.asList(
                new Box("Box1", "xx\nxx", "1"),
                new Box("Box2", "xx\nxx", "2"),
                new Box("Box3", "xx\nxx", "3")
        );

        loaderBoxesInTrucksMaxAlgService.loadBoxesInTrucks(boxes, null, null);

        List<Truck> trucks = truckRepository.getTrucks();
        assertEquals(1, trucks.size());
        assertEquals(3, trucks.get(0).getBoxes().size());
    }

    @Test
    @DisplayName("Тест: loadBoxesInTrucks должен корректно загружать коробки в грузовики с ограничением на количество грузовиков")
    void loadBoxesInTrucks_givenBoxesAndLimitTrucks_shouldLoadBoxesCorrectly() {
        List<Box> boxes = Arrays.asList(
                new Box("Box1", "xx\nxx", "1"),
                new Box("Box2", "xx\nxx", "2"),
                new Box("Box3", "xxx\nxxx", "3"),
                new Box("Box4", "xxxx\nxxxx", "4"),
                new Box("Box5", "xxxx\nxx", "5")
        );

        loaderBoxesInTrucksMaxAlgService.loadBoxesInTrucks(boxes, null, 2);

        List<Truck> trucks = truckRepository.getTrucks();
        assertEquals(2, trucks.size());
        assertEquals(4, trucks.get(0).getBoxes().size());
        assertEquals(1, trucks.get(1).getBoxes().size());
    }

    @Test
    @DisplayName("Тест: loadBoxesInTrucks должен корректно загружать коробки в грузовики с заданной формой грузовиков")
    void loadBoxesInTrucks_givenBoxesAndTrucksForms_shouldLoadBoxesCorrectly() {
        List<Box> boxes = Arrays.asList(
                new Box("Box1", "xx\nxx", "1"),
                new Box("Box2", "xx\nxx", "2"),
                new Box("Box3", "xx\nxx", "3")
        );

        String trucksForms = "4x2\n5x5";

        loaderBoxesInTrucksMaxAlgService.loadBoxesInTrucks(boxes, trucksForms, null);

        List<Truck> trucks = truckRepository.getTrucks();
        assertEquals(2, trucks.size());
        assertEquals("Truck 4x2", trucks.get(0).getTruckName());
        assertEquals("Truck 5x5", trucks.get(1).getTruckName());
        assertEquals(2, trucks.get(0).getBoxes().size());
        assertEquals(1, trucks.get(1).getBoxes().size());
    }

    @Test
    @DisplayName("Тест: loadBoxesInTrucks должен корректно загружать коробки в грузовики с заданной формой грузовиков и ограничением на количество грузовиков")
    void loadBoxesInTrucks_givenBoxesAndTrucksFormsAndLimitTrucks_shouldLoadBoxesCorrectly() {
        List<Box> boxes = Arrays.asList(
                new Box("Box1", "xx\nxx", "1"),
                new Box("Box2", "xx\nxx", "2"),
                new Box("Box3", "xxx\nxxx", "3"),
                new Box("Box4", "xxx\nxxx", "4"),
                new Box("Box5", "xx\nxx", "5")
        );

        String trucksForms = "4x4\n3x3";

        loaderBoxesInTrucksMaxAlgService.loadBoxesInTrucks(boxes, trucksForms, 2);

        List<Truck> trucks = truckRepository.getTrucks();
        assertEquals(2, trucks.size());
        assertEquals("Truck 4x4", trucks.get(0).getTruckName());
        assertEquals("Truck 3x3", trucks.get(1).getTruckName());
        assertEquals(3, trucks.get(0).getBoxes().size());
        assertEquals(1, trucks.get(1).getBoxes().size());
    }
}
