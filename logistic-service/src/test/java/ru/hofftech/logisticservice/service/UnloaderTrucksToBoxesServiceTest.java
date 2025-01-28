package ru.hofftech.logisticservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.exception.FileReadException;
import ru.hofftech.logisticservice.exception.FileWriteException;
import ru.hofftech.logisticservice.model.Truck;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnloaderTrucksToBoxesServiceTest {

    @TempDir
    Path tempDir;
    private UnloaderTrucksToBoxesService unloaderTrucksToBoxesService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        unloaderTrucksToBoxesService = new UnloaderTrucksToBoxesService();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Тест: unloadTrucksToBoxes должен сохранить информацию о коробках в файл")
    void unloadTrucksToBoxes_givenFileTrucksAndFileBoxes_shouldSaveToFile() throws IOException {
        String fileNameTrucks = tempDir.resolve("trucks.json").toString();
        String fileNameBoxes = tempDir.resolve("boxes.csv").toString();

        List<Truck> trucks = List.of(
                new Truck("Truck1", List.of(
                        new BoxDto("Box1", "xx\nxx", "1"),
                        new BoxDto("Box2", "xx\nxx", "2"))),
                new Truck("Truck2", List.of(
                        new BoxDto("Box3", "xx\nxx", "3")))
        );

        objectMapper.writeValue(new File(fileNameTrucks), trucks);

        var result = unloaderTrucksToBoxesService.unloadTrucksToBoxes(fileNameTrucks, fileNameBoxes, false);

        //assertTrue(result.contains("Результаты сохранены в файл: " + fileNameBoxes));
        assertTrue(Files.exists(Path.of(fileNameBoxes)));
    }

//    @Test
//    @DisplayName("Тест: unloadTrucksToBoxes должен вернуть информацию о коробках в виде строки")
//    void unloadTrucksToBoxes_givenFileTrucksAndNoFileBoxes_shouldReturnBoxesAsString() throws IOException {
//        String fileNameTrucks = tempDir.resolve("trucks.json").toString();
//        String fileNameBoxes = "";
//
//        List<Truck> trucks = List.of(
//                new Truck("Truck1", List.of(
//                        new BoxDto("Box1", "xx\nxx", "1"),
//                        new BoxDto("Box2", "xx\nxx", "2"))),
//                new Truck("Truck2", List.of(
//                        new BoxDto("Box3", "xx\nxx", "3")))
//        );
//
//        objectMapper.writeValue(new File(fileNameTrucks), trucks);
//
//        var result = unloaderTrucksToBoxesService.unloadTrucksToBoxes(fileNameTrucks, fileNameBoxes, false);
//
//        assertTrue(result.contains("Box1"));
//        assertTrue(result.contains("Box2"));
//        assertTrue(result.contains("Box3"));
//    }

    @Test
    @DisplayName("Тест: unloadTrucksToBoxes должен сохранить информацию о коробках с подсчетом количества в файл")
    void unloadTrucksToBoxes_givenFileTrucksAndFileBoxesWithCount_shouldSaveToFileWithCount() throws IOException {
        String fileNameTrucks = tempDir.resolve("trucks.json").toString();
        String fileNameBoxes = tempDir.resolve("boxes.csv").toString();

        List<Truck> trucks = List.of(
                new Truck("Truck1", List.of(
                        new BoxDto("Box1", "xx\nxx", "1"),
                        new BoxDto("Box2", "xx\nxx", "2"))),
                new Truck("Truck2", List.of(
                        new BoxDto("Box3", "xx\nxx", "3")))
        );

        objectMapper.writeValue(new File(fileNameTrucks), trucks);

        var result = unloaderTrucksToBoxesService.unloadTrucksToBoxes(fileNameTrucks, fileNameBoxes, true);

        //assertTrue(result.contains("Результаты сохранены в файл: " + fileNameBoxes));
        assertTrue(Files.exists(Path.of(fileNameBoxes)));
    }

    @Test
    @DisplayName("Тест: unloadTrucksToBoxes должен выбросить исключение FileReadException при ошибке чтения файла")
    void unloadTrucksToBoxes_givenFileTrucksAndFileReadException_shouldThrowFileReadException() {
        String fileNameTrucks = tempDir.resolve("trucks.json").toString();
        String fileNameBoxes = tempDir.resolve("boxes.csv").toString();

        assertThrows(FileReadException.class, () -> {
            unloaderTrucksToBoxesService.unloadTrucksToBoxes(fileNameTrucks, fileNameBoxes, false);
        });
    }

    @Test
    @DisplayName("Тест: unloadTrucksToBoxes должен выбросить исключение FileWriteException при ошибке записи файла")
    void unloadTrucksToBoxes_givenFileTrucksAndFileWriteException_shouldThrowFileWriteException() throws IOException {
        String fileNameTrucks = tempDir.resolve("trucks.json").toString();
        String fileNameBoxes = tempDir.resolve("boxes.csv").toString();

        List<Truck> trucks = List.of(
                new Truck("Truck1", List.of(
                        new BoxDto("Box1", "xx\nxx", "1"),
                        new BoxDto("Box2", "xx\nxx", "2"))),
                new Truck("Truck2", List.of(
                        new BoxDto("Box3", "xx\nxx", "3")))
        );

        objectMapper.writeValue(new File(fileNameTrucks), trucks);

        // Create a read-only file to simulate a write exception
        Files.createFile(Path.of(fileNameBoxes));
        Files.setPosixFilePermissions(Path.of(fileNameBoxes), java.nio.file.attribute.PosixFilePermissions.fromString("r--r--r--"));

        assertThrows(FileWriteException.class, () -> {
            unloaderTrucksToBoxesService.unloadTrucksToBoxes(fileNameTrucks, fileNameBoxes, false);
        });
    }

}
