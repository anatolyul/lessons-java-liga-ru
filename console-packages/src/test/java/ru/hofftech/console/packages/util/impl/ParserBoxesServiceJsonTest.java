package ru.hofftech.console.packages.util.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.service.ParserBoxesService;
import ru.hofftech.console.packages.service.converter.CommandArgConverterService;
import ru.hofftech.console.packages.service.impl.ParserBoxesServiceJson;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParserBoxesServiceJsonTest {

    @Test
    void parse_givenFileTrucks_shouldReturnCorrectBoxes(){
        List<Box> boxes;
        BoxRepository repository = new BoxRepository();
        ParserBoxesService parserBoxesService = new ParserBoxesServiceJson(repository, new ObjectMapper());
        CommandArgConverterService commandArgConverterService = new CommandArgConverterService();
        boxes = parserBoxesService.parse(commandArgConverterService.FileNameCommandToPath("import trucks.json"));

        assertThat(boxes.size()).isEqualTo(9);
    }
}