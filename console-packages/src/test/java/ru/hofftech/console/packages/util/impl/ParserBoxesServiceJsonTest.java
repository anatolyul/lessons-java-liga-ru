package ru.hofftech.console.packages.util.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.service.FormatterService;
import ru.hofftech.console.packages.service.impl.ParserBoxesServiceJson;
import ru.hofftech.console.packages.service.ParserBoxesService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParserBoxesServiceJsonTest {

    @Test
    void parse_givenFileTrucks_shouldReturnCorrectBoxes(){
        List<Box> boxes;
        ParserBoxesService parserBoxesService = new ParserBoxesServiceJson(new ObjectMapper());
        FormatterService formatterService = new FormatterService();
        boxes = parserBoxesService.parse(formatterService.FileNameCommandToPath("import trucks.json"));

        assertThat(boxes.size()).isEqualTo(6);
    }
}