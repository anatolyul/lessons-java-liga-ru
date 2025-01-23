package ru.hofftech.console.packages.service.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.repository.TruckRepository;
import ru.hofftech.console.packages.service.FormatterService;
import ru.hofftech.console.packages.service.ParserBoxesService;
import ru.hofftech.console.packages.service.converter.CommandArgConverterService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LoaderBoxesInTrucksOneToOneAlgServiceTest {

    @Test
    void loadBoxesInTrucks_givenFileBoxes_shouldReturnCorrectTrucks() {
        List<Box> boxes;
        BoxRepository repository = new BoxRepository();
        ParserBoxesService parserBoxesService = new ParserBoxesServiceTxt(repository);
        FormatterService formatterService = new FormatterService();
        CommandArgConverterService commandArgConverterService = new CommandArgConverterService();
        TruckRepository truckRepository = new TruckRepository();
        boxes = parserBoxesService.parse(commandArgConverterService.fileToPath("boxes.txt"));

        LoaderBoxesInTrucksOneToOneAlgService loadingBoxesInTruckService =
                new LoaderBoxesInTrucksOneToOneAlgService(truckRepository);
        loadingBoxesInTruckService.loadBoxesInTrucks(boxes, null, 6);
        System.out.print(formatterService.trucksToString(truckRepository.getTrucks()));
        System.out.print(formatterService.trucksToJson(truckRepository.getTrucks()));

        assertThat(truckRepository.getTrucks()).hasSize(6);
    }
}