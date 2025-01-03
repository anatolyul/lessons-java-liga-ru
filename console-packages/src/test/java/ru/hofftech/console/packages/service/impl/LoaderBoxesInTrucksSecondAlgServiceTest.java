package ru.hofftech.console.packages.service.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.service.FormatterService;
import ru.hofftech.console.packages.service.ParserBoxesService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LoaderBoxesInTrucksSecondAlgServiceTest {

    @Test
    void loadBoxesInTrucks_givenFileBoxes_shouldReturnCorrectTrucks() {
        List<Box> boxes;
        ParserBoxesService parserBoxesService = new ParserBoxesServiceTxt();
        FormatterService formatterService = new FormatterService();
        boxes = parserBoxesService.parse(formatterService.FileNameCommandToPath("import boxes.txt"));

        LoaderBoxesInTrucksSecondAlgService loadingBoxesInTruckService = new LoaderBoxesInTrucksSecondAlgService();
        java.util.List<Truck> trucks = loadingBoxesInTruckService.loadBoxesInTrucks(boxes, 2);
        System.out.print(formatterService.TrucksToString(trucks));
        System.out.print(formatterService.TrucksToJson(trucks));

        assertThat(trucks.size()).isEqualTo(1);
    }
}