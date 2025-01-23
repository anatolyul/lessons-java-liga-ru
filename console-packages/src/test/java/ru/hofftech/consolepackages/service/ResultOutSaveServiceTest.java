package ru.hofftech.consolepackages.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.hofftech.consolepackages.model.Box;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.repository.TruckRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResultOutSaveServiceTest {

    @TempDir
    Path tempDir;
    private ResultOutSaveService resultOutSaveService;
    private FileWriterService fileWriterService;
    private FormatterService formatterService;
    private TruckRepository truckRepository;

    @BeforeEach
    void setUp() {
        fileWriterService = new FileWriterService();
        formatterService = new FormatterService();
        truckRepository = new TruckRepository();
        resultOutSaveService = new ResultOutSaveService(fileWriterService, truckRepository);
    }

    @Test
    @DisplayName("Тест: saveOutResult должен сохранить результаты распределения груза в файл")
    void saveOutResult_givenBoxesAndTrucks_shouldSaveToFile() {
        String fileNameResult = tempDir.resolve("result.json").toString();

        List<Box> boxes = Arrays.asList(
                new Box("Box1", "xx\nxx", "1"),
                new Box("Box2", "xx\nxx", "2"),
                new Box("Box3", "xx\nxx", "3")
        );

        List<Truck> trucks = Arrays.asList(
                new Truck("Truck1", Arrays.asList(boxes.get(0), boxes.get(1))),
                new Truck("Truck2", Arrays.asList(boxes.get(2)))
        );

        truckRepository.setTrucks(trucks);

        String result = resultOutSaveService.saveOutResult(formatterService, boxes, fileNameResult);

        assertTrue(result.contains("Результаты распределения груза:"));
        assertTrue(result.contains("Результат сохранен в файл: " + fileNameResult));
        assertTrue(Files.exists(Path.of(fileNameResult)));
    }

    @Test
    @DisplayName("Тест: saveOutResult должен вернуть информацию о пропущенных посылках")
    void saveOutResult_givenBoxesNotInTrucks_shouldReturnMissedBoxes() {
        String fileNameResult = tempDir.resolve("result.json").toString();

        List<Box> boxes = Arrays.asList(
                new Box("Box1", "xx\nxx", "1"),
                new Box("Box2", "xx\nxx", "2"),
                new Box("Box3", "xx\nxx", "3")
        );

        List<Truck> trucks = Arrays.asList(
                new Truck("Truck1", Arrays.asList(boxes.get(0)))
        );

        truckRepository.setTrucks(trucks);

        String result = resultOutSaveService.saveOutResult(formatterService, boxes, fileNameResult);

        assertTrue(result.contains("Пропущена посылка: Box2"));
        assertTrue(result.contains("Пропущена посылка: Box3"));
    }

    @Test
    @DisplayName("Тест: saveOutResult должен вернуть пустую строку, если список коробок или грузовиков пуст")
    void saveOutResult_givenEmptyBoxesOrTrucks_shouldReturnEmptyString() {
        String fileNameResult = tempDir.resolve("result.json").toString();

        List<Box> boxes = Arrays.asList(
                new Box("Box1", "xx\nxx", "1")
        );

        List<Truck> trucks = Arrays.asList();

        truckRepository.setTrucks(trucks);

        String result = resultOutSaveService.saveOutResult(formatterService, boxes, fileNameResult);

        assertEquals("", result);
    }
}
