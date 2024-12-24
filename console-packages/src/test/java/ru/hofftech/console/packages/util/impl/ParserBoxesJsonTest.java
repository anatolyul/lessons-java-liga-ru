package ru.hofftech.console.packages.util.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.service.FormatterService;
import ru.hofftech.console.packages.util.ParserBoxes;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParserBoxesJsonTest {

    @Test
    void parse() {
        List<Box> boxes;
        ParserBoxes parserBoxes = new ParserBoxesJson(new ObjectMapper());
        FormatterService formatterService = new FormatterService();
        boxes = parserBoxes.parse(formatterService.FileNameCommandToPath("import trucks.json"));

        assertThat(boxes.size()).isEqualTo(6);
    }
}