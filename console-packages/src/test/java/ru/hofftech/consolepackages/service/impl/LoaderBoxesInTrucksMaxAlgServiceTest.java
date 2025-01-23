package ru.hofftech.consolepackages.service.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.model.Box;
import ru.hofftech.consolepackages.repository.BoxRepository;
import ru.hofftech.consolepackages.repository.TruckRepository;
import ru.hofftech.consolepackages.service.FormatterService;
import ru.hofftech.consolepackages.service.ParserBoxesService;
import ru.hofftech.consolepackages.service.converter.CommandArgConverterService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LoaderBoxesInTrucksMaxAlgServiceTest {

    @Test
    void loadBoxesInTrucks_givenFileBoxes_shouldReturnCorrectTrucks() {
        List<Box> boxes;
        BoxRepository repository = new BoxRepository();
        ParserBoxesService parserBoxesService = new ParserBoxesServiceTxt(repository);
        FormatterService formatterService = new FormatterService();
        CommandArgConverterService commandArgConverterService = new CommandArgConverterService();
        TruckRepository truckRepository = new TruckRepository();
        boxes = parserBoxesService.parse(commandArgConverterService.fileToPath("boxes.txt"));

        LoaderBoxesInTrucksMaxAlgService loadingBoxesInTruckService =
                new LoaderBoxesInTrucksMaxAlgService(truckRepository);
        loadingBoxesInTruckService.loadBoxesInTrucks(boxes, null, 2);
        System.out.print(formatterService.trucksToString(truckRepository.getTrucks()));
        System.out.print(formatterService.trucksToJson(truckRepository.getTrucks()));

        assertThat(truckRepository.getTrucks()).hasSize(1);
    }
}