package ru.hofftech.consolepackages.service.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hofftech.consolepackages.model.Box;
import ru.hofftech.consolepackages.model.Command;
import ru.hofftech.consolepackages.model.enums.Argument;
import ru.hofftech.consolepackages.repository.BoxRepository;
import ru.hofftech.consolepackages.service.CommandExecutor;
import ru.hofftech.consolepackages.service.FormatterService;
import ru.hofftech.consolepackages.service.ResultOutSaveService;
import ru.hofftech.consolepackages.service.converter.CommandArgConverterService;
import ru.hofftech.consolepackages.service.factory.LoaderBoxesInTrucksServiceFactory;
import ru.hofftech.consolepackages.service.factory.ParserBoxesServiceFactory;

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
        arguments = command.arguments();
        List<Box> boxes = getBoxes(command);
        var typeAlgorithm = commandArgConverterService.convertTypeAlgorithmStringToEnum(arguments.get(Argument.TYPE));
        if (typeAlgorithm == null) {
            log.error("Не удалось преобразовать строку алгоритма в enum. Аргумент: {}", arguments.get(Argument.TYPE));
            throw new IllegalArgumentException("Неверный тип алгоритма: " + arguments.get(Argument.TYPE));
        }

        loaderBoxesInTrucksServiceFactory
                .createLoaderBoxesInTrucksService(typeAlgorithm)
                .loadBoxesInTrucks(boxes,
                        arguments.get(Argument.TRUCKS),
                        arguments.get(Argument.LIMIT) != null
                                && !arguments.get(Argument.LIMIT).isBlank()
                                ? Integer.parseInt(arguments.get(Argument.LIMIT)) : 0);
        return resultOutSaveService.saveOutResult(formatterService,
                boxes, arguments.get(Argument.OUT_FILENAME));
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
                            command.consoleCommand(),
                            commandArgConverterService.fileToPath(boxesFile));
        } else {
            boxes = boxRepository.getBoxes();
        }
        return boxes;
    }
}
