package ru.hofftech.console.packages.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.console.packages.exception.FileReadException;
import ru.hofftech.console.packages.exception.FileWriteException;
import ru.hofftech.console.packages.model.Command;
import ru.hofftech.console.packages.model.enums.Argument;
import ru.hofftech.console.packages.service.CommandExecutor;
import ru.hofftech.console.packages.service.UnloaderTrucksToBoxesService;
import ru.hofftech.console.packages.service.converter.CommandArgConverterService;

import java.util.Map;

/**
 * Сервис для выполнения команды разгрузки грузовиков.
 */
@Service
@RequiredArgsConstructor
public class UnloadCommandService implements CommandExecutor {
    private final UnloaderTrucksToBoxesService unloaderTrucksToBoxesService;
    private final CommandArgConverterService commandArgConverterService;

    /**
     * Выполняет команду разгрузки грузовиков.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(Command command) {
        Map<Argument, String> arguments = command.arguments();
        try {
            return unloaderTrucksToBoxesService.unloadTrucksToBoxes(
                    commandArgConverterService.fileToPath(arguments.get(Argument.IN_FILENAME)),
                    arguments.get(Argument.OUT_FILENAME),
                    arguments.get(Argument.WITHCOUNT) != null
                            && arguments.get(Argument.WITHCOUNT).equals("true"));
        } catch (FileReadException | FileWriteException e) {
            return "Ошибка выполнения команды: " + e.getMessage();
        }
    }
}
