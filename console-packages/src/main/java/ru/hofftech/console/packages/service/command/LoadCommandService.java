package ru.hofftech.console.packages.service.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Command;
import ru.hofftech.console.packages.model.Truck;
import ru.hofftech.console.packages.model.enums.Argument;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.service.CommandExecutor;
import ru.hofftech.console.packages.service.FormatterService;
import ru.hofftech.console.packages.service.ResultOutSaveService;
import ru.hofftech.console.packages.service.converter.CommandArgConverterService;
import ru.hofftech.console.packages.service.factory.LoaderBoxesInTrucksServiceFactory;
import ru.hofftech.console.packages.service.factory.ParserBoxesServiceFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Сервис для выполнения команды загрузки коробок в грузовики.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoadCommandService implements CommandExecutor {
    private final LoaderBoxesInTrucksServiceFactory loaderBoxesInTrucksServiceFactory;
    private final CommandArgConverterService commandArgConverterService;
    private final FormatterService formatterService;
    private final ResultOutSaveService resultOutSaveService;
    private final ParserBoxesServiceFactory parserBoxesServiceFactory;
    private final BoxRepository boxRepository;

    private Map<Argument, String> arguments;

    /**
     * Выполняет команду загрузки коробок в грузовики.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(Command command) {
        arguments = command.getArguments();
        List<Truck> trucks = getTrucks();
        List<Box> boxes = getBoxes(command);
        trucks = loaderBoxesInTrucksServiceFactory
                .createLoaderBoxesInTrucksService(commandArgConverterService
                        .convertTypeAlgorithmStringToEnum(arguments.get(Argument.TYPE)))
                .loadBoxesInTrucks(boxes, trucks,
                        arguments.get(Argument.LIMIT) != null && !arguments.get(Argument.LIMIT).isBlank()
                                ? Integer.parseInt(arguments.get(Argument.LIMIT)) : 0);
        return resultOutSaveService.saveOutResult(formatterService,
                trucks, boxes, arguments.get(Argument.OUT_FILENAME));
    }

    /**
     * Получает список грузовиков на основе аргументов команды.
     *
     * @return список грузовиков
     */
    private List<Truck> getTrucks() {
        List<Truck> trucks = new ArrayList<>();
        String trucksForms = arguments.get(Argument.TRUCKS);
        if (trucksForms != null && !trucksForms.isEmpty()) {
            String[] truckDimensions = trucksForms.replace("n", "\n")
                    .replace("\\n", "\n").split("\n");
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

    /**
     * Получает список коробок на основе аргументов команды.
     *
     * @param command команда для выполнения
     * @return список коробок
     */
    private List<Box> getBoxes(Command command) {
        List<Box> boxes = new ArrayList<>();
        String boxesNames = arguments.get(Argument.PARCELS_TEXT);
        String boxesFile = arguments.get(Argument.PARCELS_FILE);
        if (boxesNames != null && !boxesNames.isEmpty()) {
            for (String boxName : boxesNames.replace("n", "\n")
                    .replace("\\n", "\n").split("\n")) {
                Box box = boxRepository.findBoxByName(boxName);
                if (box != null) {
                    boxes.add(box);
                } else {
                    log.warn("Посылка {} пропущена, т.к. не найдена в системе", boxName);
                }
            }
        } else if (boxesFile != null && !boxesFile.isEmpty()) {
            boxes = parserBoxesServiceFactory
                    .create(boxRepository,
                            command.getConsoleCommand(),
                            commandArgConverterService.fileToPath(boxesFile));
        } else {
            boxes = boxRepository.getBoxes();
        }
        return boxes;
    }
}
