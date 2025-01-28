package ru.hofftech.logisticservice.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.mapper.BoxMapper;
import ru.hofftech.logisticservice.repository.BoxRepository;
import ru.hofftech.logisticservice.service.BoxService;
import ru.hofftech.logisticservice.service.ParserBoxesService;
import ru.hofftech.logisticservice.service.converter.CommandArgConverterService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParserBoxesServiceJsonTest {

    @Test
    void parse_givenFileTrucks_shouldReturnCorrectBoxes(){
        List<BoxDto> boxes;
        BoxRepository boxRepository = Mockito.mock(BoxRepository.class);
        BoxMapper boxMapper = Mockito.mock(BoxMapper.class);
        BoxService boxService = new BoxService(boxRepository, boxMapper);
        ParserBoxesService parserBoxesService = new ParserBoxesServiceJson(boxService);
        CommandArgConverterService commandArgConverterService = new CommandArgConverterService();
        boxes = parserBoxesService.parse(commandArgConverterService.fileToPath("trucks.json"));

        assertThat(boxes).hasSize(2);
    }
}