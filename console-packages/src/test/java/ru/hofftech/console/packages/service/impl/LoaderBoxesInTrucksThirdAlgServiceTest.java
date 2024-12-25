package ru.hofftech.console.packages.service.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.service.FormatterService;
import ru.hofftech.console.packages.util.ParserBoxes;
import ru.hofftech.console.packages.util.impl.ParserBoxesTxt;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LoaderBoxesInTrucksThirdAlgServiceTest {

    @Test
    void loadBoxesInTrucks() {
        List<Box> boxes;
        ParserBoxes parserBoxes = new ParserBoxesTxt();
        FormatterService formatterService = new FormatterService();
        boxes = parserBoxes.parse(formatterService.FileNameCommandToPath("import boxes.txt"));

        LoaderBoxesInTrucksThirdAlgService loadingBoxesInTruckService = new LoaderBoxesInTrucksThirdAlgService();
        java.util.List<Truck> trucks = loadingBoxesInTruckService.loadBoxesInTrucks(boxes, 2);
        System.out.print(formatterService.TrucksToString(trucks));
        System.out.print(formatterService.TrucksToJson(trucks));

        assertThat(trucks.size()).isEqualTo(2);
    }
}