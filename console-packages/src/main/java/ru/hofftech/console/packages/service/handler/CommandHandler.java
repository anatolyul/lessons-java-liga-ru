package ru.hofftech.console.packages.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Command;
import ru.hofftech.console.packages.model.CommandArgument;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.model.converter.CommandArgConverter;
import ru.hofftech.console.packages.model.enums.Argument;
import ru.hofftech.console.packages.model.enums.TypeAlgorithm;
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

    public String handle(String commandString) {
        final String DELIMETR_LINES = "\n";

        Command commandArgs = commandArgConverter.parseCommandArgs(commandString);
        List<CommandArgument> commandArgument = commandArgs.getArguments();
        String algorithm = commandArgConverter.getArgumentValue(commandArgument, Argument.TYPE);
        String limit = commandArgConverter.getArgumentValue(commandArgument, Argument.LIMIT);
        String trucksForms = commandArgConverter.getArgumentValue(commandArgument, Argument.TRUCKS);
        String boxesNames = commandArgConverter.getArgumentValue(commandArgument, Argument.PARCELS_TEXT);
        String boxesFile = commandArgConverter.getArgumentValue(commandArgument, Argument.PARCELS_FILE);
        String id = commandArgConverter.getArgumentValue(commandArgument, Argument.ID);
        String name = commandArgConverter.getArgumentValue(commandArgument, Argument.NAME);
        String form = commandArgConverter.getArgumentValue(commandArgument, Argument.FORM);
        String symbol = commandArgConverter.getArgumentValue(commandArgument, Argument.SYMBOL);
        String inFile = commandArgConverter.getArgumentValue(commandArgument, Argument.IN_FILENAME);
        String outFile = commandArgConverter.getArgumentValue(commandArgument, Argument.OUT_FILENAME);
        String withcount = commandArgConverter.getArgumentValue(commandArgument, Argument.WITHCOUNT);
        String output = commandArgConverter.getArgumentValue(commandArgument, Argument.OUT);

        String result = "";

        switch (commandArgs.getCommand()) {
            case EXIT -> System.exit(0);
            case IMPORT_FILE_TXT,
                 IMPORT_FILE_JSON -> parserBoxesServiceFactory
                    .create(boxRepository, commandArgs.getCommand())
                    .parse(formatterService.FileNameCommandToPath(commandString));
            case LOAD -> {
                Integer limitTrucks = limit != null ? Integer.parseInt(limit) : 0;
                TypeAlgorithm algorithmType = commandArgConverter.convertTypeAlgorithmStringToEnum(algorithm);
                List<Truck> trucks = getTrucks(trucksForms);
                List<Box> boxes = new ArrayList<>();
                if (boxesNames != null && !boxesNames.isEmpty()) {
                    for (String boxName : boxesNames.split(DELIMETR_LINES)) {
                        boxes.add(boxRepository.findBoxByName(boxName));
                    }
                } else if (boxesFile != null && !boxesFile.isEmpty()) {
                    boxes = parserBoxesServiceFactory
                            .create(boxRepository, commandArgs.getCommand())
                            .parse(formatterService.FileToPath(boxesFile));
                } else {
                    boxes = boxRepository.getBoxes();
                }

                trucks = loaderBoxesInTrucksServiceFactory
                        .createLoaderBoxesInTrucksService(algorithmType)
                        .loadBoxesInTrucks(boxes, trucks, limitTrucks);
                result = resultOutSaveService.saveOutResult(formatterService, trucks, boxes);
            }
            case UNLOAD -> {
                boolean wcount = withcount != null && !withcount.isEmpty();
                unloaderTrucksToBoxesService.unloadTrucksToBoxes(
                        formatterService.FileToPath(inFile),
                        outFile,
                        wcount);
            }
            case BOX_CREATE -> {
                result = boxRepository.createBox(name, form, symbol);
            }
            case BOX_FIND -> {
                result = boxRepository.findBox(id, name);
            }
            case BOX_DELETE -> {
                result = boxRepository.deleteBox(name);
            }
            case BOX_EDIT -> {
                result = boxRepository.updateBox(id, name, form, symbol);
            }
            case BOX_LIST -> {
                result = boxRepository.findAll();
            }
            case UNKNOWN -> result = "Приложение не поддерживает данную команду.";
        }

        return result;
    }

    private List<Truck> getTrucks(String trucksForms) {
        List<Truck> trucks = new ArrayList<>();
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
}
