package ru.hofftech.logistictelegrambotservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hofftech.logistictelegrambotservice.enums.ConsoleCommand;
import ru.hofftech.logistictelegrambotservice.service.CommandExecutor;
import ru.hofftech.logistictelegrambotservice.service.command.BillingCommandService;
import ru.hofftech.logistictelegrambotservice.service.command.BoxCreateCommandService;
import ru.hofftech.logistictelegrambotservice.service.command.BoxDeleteCommandService;
import ru.hofftech.logistictelegrambotservice.service.command.BoxEditCommandService;
import ru.hofftech.logistictelegrambotservice.service.command.BoxFindCommandService;
import ru.hofftech.logistictelegrambotservice.service.command.BoxListCommandService;
import ru.hofftech.logistictelegrambotservice.service.command.ExitCommandService;
import ru.hofftech.logistictelegrambotservice.service.command.HelpCommandService;
import ru.hofftech.logistictelegrambotservice.service.command.ImportCommandService;
import ru.hofftech.logistictelegrambotservice.service.command.LoadCommandService;
import ru.hofftech.logistictelegrambotservice.service.command.UnknownCommandService;
import ru.hofftech.logistictelegrambotservice.service.command.UnloadCommandService;

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
}
