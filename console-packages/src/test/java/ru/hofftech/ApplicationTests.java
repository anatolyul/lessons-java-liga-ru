package ru.hofftech;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hofftech.model.Box;
import ru.hofftech.model.Truck;
import ru.hofftech.service.LoadingBoxesInTruckService;
import ru.hofftech.util.ImportBoxes;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationTests {
    private List<Box> boxes;

    @BeforeEach
    public void init() {
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource("boxes.txt")).getPath();
        this.boxes = ImportBoxes.parseFromFile(filePath);
    }

    @Test
    public void testSimpleAlg() {
        LoadingBoxesInTruckService loadingBoxesInTruckService = new LoadingBoxesInTruckService();
        List<Truck> trucks = loadingBoxesInTruckService.simpleAlg(this.boxes);

        assertThat(trucks.size()).isEqualTo(6);
    }

    @Test
    public void testComplexAlg() {
        LoadingBoxesInTruckService loadingBoxesInTruckService = new LoadingBoxesInTruckService();
        List<Truck> trucks = loadingBoxesInTruckService.complexAlg(this.boxes);

        assertThat(trucks.size()).isEqualTo(1);
    }
}
