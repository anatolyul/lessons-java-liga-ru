package ru.hofftech.console.packages.service.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.service.FormatterService;
import ru.hofftech.console.packages.service.ParserBoxesService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LoaderBoxesInTrucksMaxAlgServiceTest {

    @Test
    void loadBoxesInTrucks_givenFileBoxes_shouldReturnCorrectTrucks() {
        List<Box> boxes;
        BoxRepository repository = new BoxRepository();
        ParserBoxesService parserBoxesService = new ParserBoxesServiceTxt(repository);
        FormatterService formatterService = new FormatterService();
        boxes = parserBoxesService.parse(formatterService.FileNameCommandToPath("import boxes.txt"));

        LoaderBoxesInTrucksMaxAlgService loadingBoxesInTruckService = new LoaderBoxesInTrucksMaxAlgService();
        List<Truck> trucks = loadingBoxesInTruckService.loadBoxesInTrucks(boxes, new ArrayList<>(), 2);
        System.out.print(formatterService.TrucksToString(trucks));
        System.out.print(formatterService.TrucksToJson(trucks));

        assertThat(trucks.size()).isEqualTo(1);
    }
}