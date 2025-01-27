package ru.hofftech.logisticservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.ImportParamDto;
import ru.hofftech.logisticservice.dto.LoadParamDto;
import ru.hofftech.logisticservice.dto.ResponseCommandDto;
import ru.hofftech.logisticservice.dto.UnloadParamDto;
import ru.hofftech.logisticservice.model.Command;
import ru.hofftech.logisticservice.model.enums.Argument;
import ru.hofftech.logisticservice.model.enums.ConsoleCommand;
import ru.hofftech.logisticservice.service.handler.CommandHandler;

import java.util.EnumMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoxActionService {

    private final CommandHandler commandHandler;

    public ResponseCommandDto load(LoadParamDto loadParamDto) {
        Map<Argument, String> arguments = new EnumMap<>(Argument.class);
        arguments.put(Argument.PARCELS_TEXT, loadParamDto.getParcelsText());
        arguments.put(Argument.PARCELS_FILE, loadParamDto.getParcelsFile());
        arguments.put(Argument.TRUCKS, loadParamDto.getTrucks());
        arguments.put(Argument.TYPE, loadParamDto.getType());
        arguments.put(Argument.OUT_FILENAME, loadParamDto.getOutFilename());
        String result = commandHandler.handle(
                Command.builder()
                        .consoleCommand(ConsoleCommand.LOAD)
                        .arguments(arguments)
                        .build());

        return ResponseCommandDto.builder().resultCommandExecuted(result).build();
    }

    public ResponseCommandDto unload(UnloadParamDto unloadParamDto) {
        Map<Argument, String> arguments = new EnumMap<>(Argument.class);
        arguments.put(Argument.IN_FILENAME, unloadParamDto.getInFilename());
        arguments.put(Argument.OUT_FILENAME, unloadParamDto.getOutFilename());
        arguments.put(Argument.WITHCOUNT, String.valueOf(unloadParamDto.isWithCount()));

        String result = commandHandler.handle(
                Command.builder()
                        .consoleCommand(ConsoleCommand.UNLOAD)
                        .arguments(arguments)
                        .build());

        return ResponseCommandDto.builder().resultCommandExecuted(result).build();
    }

    public ResponseCommandDto importFile(ImportParamDto importParamDto) {
        Map<Argument, String> arguments = new EnumMap<>(Argument.class);
        arguments.put(Argument.IMPORT_FILENAME, importParamDto.getFilename());

        String result = commandHandler.handle(
                Command.builder()
                        .consoleCommand(ConsoleCommand.fromExtension(importParamDto.getFilename()))
                        .arguments(arguments)
                        .build());

        return ResponseCommandDto.builder().resultCommandExecuted(result).build();
    }
}
