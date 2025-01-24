package ru.hofftech.consolepackages.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.hofftech.consolepackages.model.Box;
import ru.hofftech.consolepackages.model.Truck;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResultOutSaveServiceTest {

    @TempDir
    Path tempDir;
    private ResultOutSaveService resultOutSaveService;

    @BeforeEach
    void setUp() {
        resultOutSaveService = new ResultOutSaveService(new FileWriterService(), new FormatterService());
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

        String result = resultOutSaveService.saveOutResult(boxes, trucks, fileNameResult);

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

        List<Truck> trucks = List.of(
                new Truck("Truck1", Collections.singletonList(boxes.getFirst()))
        );

        String result = resultOutSaveService.saveOutResult(boxes, trucks, fileNameResult);

        assertTrue(result.contains("Пропущена посылка: Box2"));
        assertTrue(result.contains("Пропущена посылка: Box3"));
    }

    @Test
    @DisplayName("Тест: saveOutResult должен вернуть пустую строку, если список коробок или грузовиков пуст")
    void saveOutResult_givenEmptyBoxesOrTrucks_shouldReturnEmptyString() {
        String fileNameResult = tempDir.resolve("result.json").toString();

        List<Box> boxes = List.of(
                new Box("Box1", "xx\nxx", "1")
        );

        List<Truck> trucks = List.of();

        String result = resultOutSaveService.saveOutResult(boxes, trucks, fileNameResult);

        assertEquals("", result);
    }
}
