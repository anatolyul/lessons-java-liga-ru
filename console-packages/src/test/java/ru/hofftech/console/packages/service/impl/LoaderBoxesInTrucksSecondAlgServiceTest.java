package ru.hofftech.console.packages.service.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.util.ParserBoxes;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LoaderBoxesInTrucksSecondAlgServiceTest {

    @Test
    void loadBoxesInTrucks() {
        List<Box> boxes;
        ParserBoxes parserBoxes = new ParserBoxes();
        boxes = parserBoxes.parseFromFile("import boxes.txt");

        LoaderBoxesInTrucksSecondAlgService loadingBoxesInTruckService = new LoaderBoxesInTrucksSecondAlgService();
        java.util.List<Truck> trucks = loadingBoxesInTruckService.loadBoxesInTrucks(boxes);

        assertThat(trucks.size()).isEqualTo(1);
    }
}