package ru.hofftech.consolepackages.service.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.model.Box;
import ru.hofftech.consolepackages.repository.BoxRepository;
import ru.hofftech.consolepackages.service.ParserBoxesService;
import ru.hofftech.consolepackages.service.converter.CommandArgConverterService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParserBoxesServiceJsonTest {

    @Test
    void parse_givenFileTrucks_shouldReturnCorrectBoxes(){
        List<Box> boxes;
        BoxRepository repository = new BoxRepository();
        ParserBoxesService parserBoxesService = new ParserBoxesServiceJson(repository);
        CommandArgConverterService commandArgConverterService = new CommandArgConverterService();
        boxes = parserBoxesService.parse(commandArgConverterService.fileToPath("trucks.json"));

        assertThat(boxes).hasSize(9);
    }
}