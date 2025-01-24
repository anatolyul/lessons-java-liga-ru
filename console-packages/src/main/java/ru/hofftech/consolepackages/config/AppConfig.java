package ru.hofftech.consolepackages.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hofftech.consolepackages.model.enums.ConsoleCommand;
import ru.hofftech.consolepackages.model.enums.TypeAlgorithm;
import ru.hofftech.consolepackages.service.CommandExecutor;
import ru.hofftech.consolepackages.service.LoaderBoxesInTrucksService;
import ru.hofftech.consolepackages.service.ParserBoxesService;
import ru.hofftech.consolepackages.service.command.BillingCommandService;
import ru.hofftech.consolepackages.service.command.BoxCreateCommandService;
import ru.hofftech.consolepackages.service.command.BoxDeleteCommandService;
import ru.hofftech.consolepackages.service.command.BoxEditCommandService;
import ru.hofftech.consolepackages.service.command.BoxFindCommandService;
import ru.hofftech.consolepackages.service.command.BoxListCommandService;
import ru.hofftech.consolepackages.service.command.ExitCommandService;
import ru.hofftech.consolepackages.service.command.HelpCommandService;
import ru.hofftech.consolepackages.service.command.ImportCommandService;
import ru.hofftech.consolepackages.service.command.LoadCommandService;
import ru.hofftech.consolepackages.service.command.UnknownCommandService;
import ru.hofftech.consolepackages.service.command.UnloadCommandService;
import ru.hofftech.consolepackages.service.impl.LoaderBoxesInTrucksMaxAlgService;
import ru.hofftech.consolepackages.service.impl.LoaderBoxesInTrucksOneToOneAlgService;
import ru.hofftech.consolepackages.service.impl.LoaderBoxesInTrucksUniformAlgService;
import ru.hofftech.consolepackages.service.impl.ParserBoxesServiceCsv;
import ru.hofftech.consolepackages.service.impl.ParserBoxesServiceJson;
import ru.hofftech.consolepackages.service.impl.ParserBoxesServiceTxt;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    public Map<ConsoleCommand, CommandExecutor> createCommandsMap(
            ExitCommandService exitCommandService,
            HelpCommandService helpCommandService,
            ImportCommandService importCommandService,
            LoadCommandService loadCommandService,
            UnloadCommandService unloadCommandService,
            BoxCreateCommandService boxCreateCommandService,
            BoxFindCommandService boxFindCommandService,
            BoxDeleteCommandService boxDeleteCommandService,
            BoxEditCommandService boxEditCommandService,
            BoxListCommandService boxListCommandService,
            BillingCommandService billingCommandService,
            UnknownCommandService unknownCommandService) {

        Map<ConsoleCommand, CommandExecutor> map = new EnumMap<>(ConsoleCommand.class);
        map.put(ConsoleCommand.EXIT, exitCommandService);
        map.put(ConsoleCommand.HELP, helpCommandService);
        map.put(ConsoleCommand.IMPORT_FILE_TXT, importCommandService);
        map.put(ConsoleCommand.IMPORT_FILE_JSON, importCommandService);
        map.put(ConsoleCommand.LOAD, loadCommandService);
        map.put(ConsoleCommand.UNLOAD, unloadCommandService);
        map.put(ConsoleCommand.BOX_CREATE, boxCreateCommandService);
        map.put(ConsoleCommand.BOX_FIND, boxFindCommandService);
        map.put(ConsoleCommand.BOX_DELETE, boxDeleteCommandService);
        map.put(ConsoleCommand.BOX_EDIT, boxEditCommandService);
        map.put(ConsoleCommand.BOX_LIST, boxListCommandService);
        map.put(ConsoleCommand.BILLING, billingCommandService);
        map.put(ConsoleCommand.UNKNOWN, unknownCommandService);

        return map;
    }

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
