package ru.hofftech.console.packages.service.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.service.FormatterService;
import ru.hofftech.console.packages.service.ParserBoxesService;
import ru.hofftech.console.packages.service.converter.CommandArgConverterService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LoaderBoxesInTrucksUniformAlgServiceTest {

    @Test
    void loadBoxesInTrucks_givenFileBoxes_shouldReturnCorrectTrucks() {
        List<Box> boxes;
        BoxRepository repository = new BoxRepository();
        ParserBoxesService parserBoxesService = new ParserBoxesServiceTxt(repository);
        FormatterService formatterService = new FormatterService();
        CommandArgConverterService commandArgConverterService = new CommandArgConverterService();
        boxes = parserBoxesService.parse(commandArgConverterService.fileToPath("boxes.txt"));

        LoaderBoxesInTrucksUniformAlgService loadingBoxesInTruckService = new LoaderBoxesInTrucksUniformAlgService();
        List<Truck> trucks = loadingBoxesInTruckService.loadBoxesInTrucks(boxes, new ArrayList<>(), 2);
        System.out.print(formatterService.trucksToString(trucks));
        System.out.print(formatterService.trucksToJson(trucks));

        assertThat(trucks).hasSize(2);
    }
}