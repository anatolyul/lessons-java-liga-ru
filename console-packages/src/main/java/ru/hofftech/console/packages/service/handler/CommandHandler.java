package ru.hofftech.console.packages.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Command;
import ru.hofftech.console.packages.model.CommandArgument;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.model.converter.CommandArgConverter;
import ru.hofftech.console.packages.model.enums.Argument;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.service.FormatterService;
import ru.hofftech.console.packages.service.ResultOutSaveService;
import ru.hofftech.console.packages.service.UnloaderTrucksToBoxesService;
import ru.hofftech.console.packages.service.factory.LoaderBoxesInTrucksServiceFactory;
import ru.hofftech.console.packages.service.factory.ParserBoxesServiceFactory;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CommandHandler {
    private final CommandArgConverter commandArgConverter;
    private final FormatterService formatterService;
    private final ParserBoxesServiceFactory parserBoxesServiceFactory;
    private final LoaderBoxesInTrucksServiceFactory loaderBoxesInTrucksServiceFactory;
    private final UnloaderTrucksToBoxesService unloaderTrucksToBoxesService;
    private final BoxRepository boxRepository;
    private final ResultOutSaveService resultOutSaveService;

    private Command commandArgs;
    private List<CommandArgument> commandArgument;

    public String handle(String commandString) {
        commandArgs = commandArgConverter.parseCommandArgs(commandString);
        commandArgument = commandArgs.getArguments();

        String result = "";

        switch (commandArgs.getCommand()) {
            case EXIT -> System.exit(0);
            case IMPORT_FILE_TXT,
                 IMPORT_FILE_JSON -> parserBoxesServiceFactory
                    .create(boxRepository, commandArgs.getCommand())
                    .parse(formatterService.FileNameCommandToPath(commandString));
            case LOAD -> {
                List<Truck> trucks = getTrucks();
                List<Box> boxes = getBoxes();
                trucks = loaderBoxesInTrucksServiceFactory
                        .createLoaderBoxesInTrucksService(commandArgConverter
                                .convertTypeAlgorithmStringToEnum(getArgValue(Argument.TYPE)))
                        .loadBoxesInTrucks(boxes, trucks,
                                getArgValue(Argument.LIMIT) != null ? Integer.parseInt(getArgValue(Argument.LIMIT)) : 0);
                result = resultOutSaveService.saveOutResult(formatterService,
                        trucks, boxes, getArgValue(Argument.OUT_FILENAME));
            }
            case UNLOAD -> result = unloaderTrucksToBoxesService.unloadTrucksToBoxes(
                    formatterService.FileToPath(getArgValue(Argument.IN_FILENAME)),
                    getArgValue(Argument.OUT_FILENAME),
                    getArgValue(Argument.WITHCOUNT) != null);
            case BOX_CREATE -> result = boxRepository.createBox(getArgValue(Argument.NAME),
                    getArgValue(Argument.FORM),
                    getArgValue(Argument.SYMBOL));
            case BOX_FIND -> result = boxRepository.findBox(getArgValue(Argument.ID),
                    getArgValue(Argument.NAME));
            case BOX_DELETE -> result = boxRepository.deleteBox(getArgValue(Argument.NAME));
            case BOX_EDIT -> result = boxRepository.updateBox(getArgValue(Argument.ID),
                    getArgValue(Argument.NAME),
                    getArgValue(Argument.FORM),
                    getArgValue(Argument.SYMBOL));
            case BOX_LIST -> result = boxRepository.findAll();
            case UNKNOWN -> result = "Приложение не поддерживает данную команду.";
        }

        return result;
    }

    private List<Truck> getTrucks() {
        List<Truck> trucks = new ArrayList<>();
        String trucksForms = getArgValue(Argument.TRUCKS);
        if (trucksForms != null && !trucksForms.isEmpty()) {
            String[] truckDimensions = trucksForms.split("\n");
            for (String dimension : truckDimensions) {
                String[] sizes = dimension.split("x");
                Truck truck = new Truck("Truck " + dimension,
                        Integer.parseInt(sizes[0]),
                        Integer.parseInt(sizes[1]));
                trucks.add(truck);
            }
        }
        return trucks;
    }

    private List<Box> getBoxes() {
        List<Box> boxes = new ArrayList<>();
        String boxesNames = getArgValue(Argument.PARCELS_TEXT);
        String boxesFile = getArgValue(Argument.PARCELS_FILE);
        if (boxesNames != null && !boxesNames.isEmpty()) {
            for (String boxName : boxesNames.split("\n")) {
                Box box = boxRepository.findBoxByName(boxName);
                if (box != null) {
                    boxes.add(box);
                } else {
                    log.warn("Посылка {} пропущена, т.к. не найдена в системе", boxName);
                }
            }
        } else if (boxesFile != null && !boxesFile.isEmpty()) {
            boxes = parserBoxesServiceFactory
                    .create(boxRepository, commandArgs.getCommand())
                    .parse(formatterService.FileToPath(boxesFile));
        } else {
            boxes = boxRepository.getBoxes();
        }
        return boxes;
    }

    private String getArgValue(Argument argument) {
        return commandArgConverter.getArgumentValue(commandArgument, argument);
    }
}
