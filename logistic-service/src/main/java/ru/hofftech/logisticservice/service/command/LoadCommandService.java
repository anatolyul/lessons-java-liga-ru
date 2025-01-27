package ru.hofftech.logisticservice.service.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.model.Command;
import ru.hofftech.logisticservice.model.Truck;
import ru.hofftech.logisticservice.model.TruckForm;
import ru.hofftech.logisticservice.model.enums.Argument;
import ru.hofftech.logisticservice.service.BoxService;
import ru.hofftech.logisticservice.service.CommandExecutor;
import ru.hofftech.logisticservice.service.ResultOutSaveService;
import ru.hofftech.logisticservice.service.converter.CommandArgConverterService;
import ru.hofftech.logisticservice.service.factory.LoaderBoxesInTrucksServiceFactory;
import ru.hofftech.logisticservice.service.factory.ParserBoxesServiceFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Сервис для выполнения команды загрузки коробок в грузовики.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoadCommandService implements CommandExecutor {
    private final LoaderBoxesInTrucksServiceFactory loaderBoxesInTrucksServiceFactory;
    private final CommandArgConverterService commandArgConverterService;
    private final ResultOutSaveService resultOutSaveService;
    private final ParserBoxesServiceFactory parserBoxesServiceFactory;
    private final BoxService boxService;

    private Map<Argument, String> arguments;

    /**
     * Выполняет команду загрузки коробок в грузовики.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(Command command) {
        arguments = command.arguments();
        List<BoxDto> boxes = findBoxesByCommand(command);
        var typeAlgorithm = commandArgConverterService.convertTypeAlgorithmStringToEnum(arguments.get(Argument.TYPE));
        if (typeAlgorithm == null) {
            log.error("Не удалось преобразовать строку алгоритма в enum. Аргумент: {}", arguments.get(Argument.TYPE));
            throw new IllegalArgumentException("Неверный тип алгоритма: " + arguments.get(Argument.TYPE));
        }

        TruckForm trucksForm = TruckForm.fromString(arguments.get(Argument.TRUCKS));

        List<Truck> trucks = loaderBoxesInTrucksServiceFactory
                .createLoaderBoxesInTrucksService(typeAlgorithm)
                .loadBoxesInTrucks(boxes,
                        trucksForm,
                        arguments.get(Argument.LIMIT) != null
                                && !arguments.get(Argument.LIMIT).isBlank()
                                ? Integer.parseInt(arguments.get(Argument.LIMIT)) : 0);
        return resultOutSaveService.saveOutResult(boxes, trucks, arguments.get(Argument.OUT_FILENAME));
    }


    /**
     * Получает список коробок на основе аргументов команды.
     *
     * @param command команда для выполнения
     * @return список коробок
     */
    private List<BoxDto> findBoxesByCommand(Command command) {
        List<BoxDto> boxes = new ArrayList<>();
        String boxesNames = arguments.get(Argument.PARCELS_TEXT);
        String boxesFile = arguments.get(Argument.PARCELS_FILE);
        if (boxesNames != null && !boxesNames.isEmpty()) {
            for (String boxName : boxesNames.replace("n", "\n")
                    .replace("\\n", "\n").split("\n")) {
                BoxDto box = boxService.findByName(boxName);
                if (box != null) {
                    boxes.add(box);
                } else {
                    log.warn("Посылка {} пропущена, т.к. не найдена в системе", boxName);
                }
            }
        } else if (boxesFile != null && !boxesFile.isEmpty()) {
            boxes = parserBoxesServiceFactory
                    .create(command.consoleCommand(),
                            commandArgConverterService.fileToPath(boxesFile));
        } else {
            boxes = boxService.findAll();
        }
        return boxes;
    }
}
