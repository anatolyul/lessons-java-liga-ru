package ru.hofftech.logistictelegrambotservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logistictelegrambotservice.dto.CommandDto;
import ru.hofftech.logistictelegrambotservice.enums.Argument;
import ru.hofftech.logistictelegrambotservice.service.CommandExecutor;
import ru.hofftech.logistictelegrambotservice.service.LogisticService;

import java.util.Map;

/**
 * Сервис для выполнения команды разгрузки грузовиков.
 */
@Service
@RequiredArgsConstructor
public class UnloadCommandService implements CommandExecutor {

    private final LogisticService logisticService;

    /**
     * Выполняет команду разгрузки грузовиков.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(CommandDto command) {
        Map<Argument, String> arguments = command.getArguments();
//        try {
//            return unloaderTrucksToBoxesService.unloadTrucksToBoxes(
//                    commandArgConverterService.fileToPath(arguments.get(Argument.IN_FILENAME)),
//                    arguments.get(Argument.OUT_FILENAME),
//                    arguments.get(Argument.WITHCOUNT) != null
//                            && arguments.get(Argument.WITHCOUNT).equals("true"));
//        } catch (FileReadException | FileWriteException e) {
//            return "Ошибка выполнения команды: " + e.getMessage();
//        }
        return "";
    }
}
