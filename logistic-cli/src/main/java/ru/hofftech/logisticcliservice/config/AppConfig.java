package ru.hofftech.logisticcliservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hofftech.logisticcliservice.dto.command.BaseCommandDto;
import ru.hofftech.logisticcliservice.dto.command.BillingCommandDto;
import ru.hofftech.logisticcliservice.dto.command.BoxCreateCommandDto;
import ru.hofftech.logisticcliservice.dto.command.BoxDeleteCommandDto;
import ru.hofftech.logisticcliservice.dto.command.BoxEditCommandDto;
import ru.hofftech.logisticcliservice.dto.command.BoxFindCommandDto;
import ru.hofftech.logisticcliservice.dto.command.BoxListCommandDto;
import ru.hofftech.logisticcliservice.dto.command.HelpCommandDto;
import ru.hofftech.logisticcliservice.dto.command.ImportCommandDto;
import ru.hofftech.logisticcliservice.dto.command.LoadCommandDto;
import ru.hofftech.logisticcliservice.dto.command.UnknownCommandDto;
import ru.hofftech.logisticcliservice.dto.command.UnloadCommandDto;
import ru.hofftech.logisticcliservice.service.CommandExecutor;
import ru.hofftech.logisticcliservice.service.command.BillingCommandService;
import ru.hofftech.logisticcliservice.service.command.BoxCreateCommandService;
import ru.hofftech.logisticcliservice.service.command.BoxDeleteCommandService;
import ru.hofftech.logisticcliservice.service.command.BoxEditCommandService;
import ru.hofftech.logisticcliservice.service.command.BoxFindCommandService;
import ru.hofftech.logisticcliservice.service.command.BoxListCommandService;
import ru.hofftech.logisticcliservice.service.command.HelpCommandService;
import ru.hofftech.logisticcliservice.service.command.ImportCommandService;
import ru.hofftech.logisticcliservice.service.command.LoadCommandService;
import ru.hofftech.logisticcliservice.service.command.UnknownCommandService;
import ru.hofftech.logisticcliservice.service.command.UnloadCommandService;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    public Map<Class<? extends BaseCommandDto>, CommandExecutor> createCommandsMap(
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

        Map<Class<? extends BaseCommandDto>, CommandExecutor> map = new HashMap<>();
        map.put(HelpCommandDto.class, helpCommandService);
        map.put(ImportCommandDto.class, importCommandService);
        map.put(LoadCommandDto.class, loadCommandService);
        map.put(UnloadCommandDto.class, unloadCommandService);
        map.put(BoxCreateCommandDto.class, boxCreateCommandService);
        map.put(BoxFindCommandDto.class, boxFindCommandService);
        map.put(BoxDeleteCommandDto.class, boxDeleteCommandService);
        map.put(BoxEditCommandDto.class, boxEditCommandService);
        map.put(BoxListCommandDto.class, boxListCommandService);
        map.put(BillingCommandDto.class, billingCommandService);
        map.put(UnknownCommandDto.class, unknownCommandService);

        return map;
    }
}
