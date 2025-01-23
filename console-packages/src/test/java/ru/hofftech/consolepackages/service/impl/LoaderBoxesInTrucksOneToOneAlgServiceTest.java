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

class LoaderBoxesInTrucksOneToOneAlgServiceTest {

    private LoaderBoxesInTrucksOneToOneAlgService loaderBoxesInTrucksOneToOneAlgService;
    private TruckRepository truckRepository;

    @BeforeEach
    void setUp() {
        truckRepository = new TruckRepository();
        loaderBoxesInTrucksOneToOneAlgService = new LoaderBoxesInTrucksOneToOneAlgService(truckRepository);
    }

    @Test
    @DisplayName("Тест: loadBoxesInTrucks должен корректно загружать коробки в грузовики без ограничения на количество грузовиков")
    void loadBoxesInTrucks_givenBoxesAndNoLimitTrucks_shouldLoadBoxesCorrectly() {
        List<Box> boxes = Arrays.asList(
                new Box("Box1", "xx\nxx", "1"),
                new Box("Box2", "xx\nxx", "2"),
                new Box("Box3", "xx\nxx", "3")
        );

        loaderBoxesInTrucksOneToOneAlgService.loadBoxesInTrucks(boxes, null, 0);

        List<Truck> trucks = truckRepository.getTrucks();
        assertEquals(3, trucks.size());
        assertEquals(1, trucks.get(0).getBoxes().size());
        assertEquals(1, trucks.get(1).getBoxes().size());
        assertEquals(1, trucks.get(2).getBoxes().size());
    }

    @Test
    @DisplayName("Тест: loadBoxesInTrucks должен корректно загружать коробки в грузовики с ограничением на количество грузовиков")
    void loadBoxesInTrucks_givenBoxesAndLimitTrucks_shouldLoadBoxesCorrectly() {
        List<Box> boxes = Arrays.asList(
                new Box("Box1", "xx\nxx", "1"),
                new Box("Box2", "xx\nxx", "2"),
                new Box("Box3", "xx\nxx", "3"),
                new Box("Box4", "xx\nxx", "4"),
                new Box("Box5", "xx\nxx", "5")
        );

        loaderBoxesInTrucksOneToOneAlgService.loadBoxesInTrucks(boxes, null, 3);

        List<Truck> trucks = truckRepository.getTrucks();
        assertEquals(3, trucks.size());
        assertEquals(1, trucks.get(0).getBoxes().size());
        assertEquals(1, trucks.get(1).getBoxes().size());
        assertEquals(1, trucks.get(2).getBoxes().size());
    }

    @Test
    @DisplayName("Тест: loadBoxesInTrucks должен корректно загружать коробки в грузовики с заданной формой грузовиков")
    void loadBoxesInTrucks_givenBoxesAndTrucksForms_shouldLoadBoxesCorrectly() {
        List<Box> boxes = Arrays.asList(
                new Box("Box1", "xx\nxx", "1"),
                new Box("Box2", "xx\nxx", "2"),
                new Box("Box3", "xx\nxx", "3")
        );

        String trucksForms = "3x3\n2x2\n6x6";

        loaderBoxesInTrucksOneToOneAlgService.loadBoxesInTrucks(boxes, trucksForms, null);

        List<Truck> trucks = truckRepository.getTrucks();
        assertEquals(3, trucks.size());
        assertEquals("Truck 3x3", trucks.get(0).getTruckName());
        assertEquals("Truck 2x2", trucks.get(1).getTruckName());
        assertEquals("Truck 6x6", trucks.get(2).getTruckName());
        assertEquals(1, trucks.get(0).getBoxes().size());
        assertEquals(1, trucks.get(1).getBoxes().size());
        assertEquals(1, trucks.get(2).getBoxes().size());
    }

    @Test
    @DisplayName("Тест: loadBoxesInTrucks должен корректно загружать коробки в грузовики с заданной формой грузовиков и ограничением на количество грузовиков")
    void loadBoxesInTrucks_givenBoxesAndTrucksFormsAndLimitTrucks_shouldLoadBoxesCorrectly() {
        List<Box> boxes = Arrays.asList(
                new Box("Box1", "xx\nxx", "1"),
                new Box("Box2", "xx\nxx", "2"),
                new Box("Box3", "xx\nxx", "3"),
                new Box("Box4", "xx\nxx", "4"),
                new Box("Box5", "xx\nxx", "5")
        );

        String trucksForms = "3x3\n2x2\n6x6";

        loaderBoxesInTrucksOneToOneAlgService.loadBoxesInTrucks(boxes, trucksForms, 3);

        List<Truck> trucks = truckRepository.getTrucks();
        assertEquals(3, trucks.size());
        assertEquals("Truck 3x3", trucks.get(0).getTruckName());
        assertEquals("Truck 2x2", trucks.get(1).getTruckName());
        assertEquals("Truck 6x6", trucks.get(2).getTruckName());
        assertEquals(1, trucks.get(0).getBoxes().size());
        assertEquals(1, trucks.get(1).getBoxes().size());
        assertEquals(1, trucks.get(2).getBoxes().size());
    }
}
