package ru.hofftech.logisticservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hofftech.logisticservice.model.enums.ConsoleCommand;
import ru.hofftech.logisticservice.model.enums.TypeAlgorithm;
import ru.hofftech.logisticservice.service.LoaderBoxesInTrucksService;
import ru.hofftech.logisticservice.service.ParserBoxesService;
import ru.hofftech.logisticservice.service.impl.LoaderBoxesInTrucksMaxAlgService;
import ru.hofftech.logisticservice.service.impl.LoaderBoxesInTrucksOneToOneAlgService;
import ru.hofftech.logisticservice.service.impl.LoaderBoxesInTrucksUniformAlgService;
import ru.hofftech.logisticservice.service.impl.ParserBoxesServiceCsv;
import ru.hofftech.logisticservice.service.impl.ParserBoxesServiceJson;
import ru.hofftech.logisticservice.service.impl.ParserBoxesServiceTxt;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    public Map<TypeAlgorithm, LoaderBoxesInTrucksService> createLoaderBoxesInTrucksMap(
            LoaderBoxesInTrucksOneToOneAlgService loaderBoxesInTrucksOneToOneAlgService,
            LoaderBoxesInTrucksMaxAlgService loaderBoxesInTrucksMaxAlgService,
            LoaderBoxesInTrucksUniformAlgService loaderBoxesInTrucksUniformAlgService) {

        Map<TypeAlgorithm, LoaderBoxesInTrucksService> map = new EnumMap<>(TypeAlgorithm.class);
        map.put(TypeAlgorithm.ONE_TO_ONE, loaderBoxesInTrucksOneToOneAlgService);
        map.put(TypeAlgorithm.MAXIMUM_LOAD, loaderBoxesInTrucksMaxAlgService);
        map.put(TypeAlgorithm.UNIFORM_LOAD, loaderBoxesInTrucksUniformAlgService);

        return map;
    }

    @Bean
    public Map<ConsoleCommand, ParserBoxesService> createParserBoxesMap(
            ParserBoxesServiceJson parserBoxesServiceJson,
            ParserBoxesServiceTxt parserBoxesServiceTxt,
            ParserBoxesServiceCsv parserBoxesServiceCsv) {

        Map<ConsoleCommand, ParserBoxesService> map = new EnumMap<>(ConsoleCommand.class);
        map.put(ConsoleCommand.IMPORT_FILE_JSON, parserBoxesServiceJson);
        map.put(ConsoleCommand.IMPORT_FILE_TXT, parserBoxesServiceTxt);
        map.put(ConsoleCommand.LOAD, parserBoxesServiceCsv);

        return map;
    }
}
