package ru.hofftech.logistictelegrambotservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logistictelegrambotservice.dto.CommandDto;
import ru.hofftech.logistictelegrambotservice.enums.Argument;
import ru.hofftech.logistictelegrambotservice.service.CommandExecutor;
import ru.hofftech.logistictelegrambotservice.service.LogisticService;

import java.util.Map;

/**
 * Сервис для выполнения команды загрузки коробок в грузовики.
 */
@Service
@RequiredArgsConstructor
public class LoadCommandService implements CommandExecutor {

    private final LogisticService logisticService;

    private Map<Argument, String> arguments;

    /**
     * Выполняет команду загрузки коробок в грузовики.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(CommandDto command) {
//        arguments = command.arguments();
//        List<BoxDto> boxes = findBoxesByCommand(command);
//        var typeAlgorithm = commandArgConverterService.convertTypeAlgorithmStringToEnum(arguments.get(Argument.TYPE));
//        if (typeAlgorithm == null) {
//            log.error("Не удалось преобразовать строку алгоритма в enum. Аргумент: {}", arguments.get(Argument.TYPE));
//            throw new IllegalArgumentException("Неверный тип алгоритма: " + arguments.get(Argument.TYPE));
//        }
//
//        TruckForm trucksForm = TruckForm.fromString(arguments.get(Argument.TRUCKS));
//
//        List<Truck> trucks = loaderBoxesInTrucksServiceFactory
//                .createLoaderBoxesInTrucksService(typeAlgorithm)
//                .loadBoxesInTrucks(boxes,
//                        trucksForm,
//                        arguments.get(Argument.LIMIT) != null
//                                && !arguments.get(Argument.LIMIT).isBlank()
//                                ? Integer.parseInt(arguments.get(Argument.LIMIT)) : 0);
//        return resultOutSaveService.saveOutResult(boxes, trucks, arguments.get(Argument.OUT_FILENAME));
        return "";
    }


    /**
     * Получает список коробок на основе аргументов команды.
     *
     * @param command команда для выполнения
     * @return список коробок
     */
//    private List<BoxDto> findBoxesByCommand(Command command) {
//        List<BoxDto> boxes = new ArrayList<>();
//        String boxesNames = arguments.get(Argument.PARCELS_TEXT);
//        String boxesFile = arguments.get(Argument.PARCELS_FILE);
//        if (boxesNames != null && !boxesNames.isEmpty()) {
//            for (String boxName : boxesNames.replace("n", "\n")
//                    .replace("\\n", "\n").split("\n")) {
//                BoxDto box = boxService.findByName(boxName);
//                if (box != null) {
//                    boxes.add(box);
//                } else {
//                    log.warn("Посылка {} пропущена, т.к. не найдена в системе", boxName);
//                }
//            }
//        } else if (boxesFile != null && !boxesFile.isEmpty()) {
//            boxes = parserBoxesServiceFactory
//                    .create(command.consoleCommand(),
//                            commandArgConverterService.fileToPath(boxesFile));
//        } else {
//            boxes = boxService.findAll();
//        }
//        return boxes;
//    }
}
