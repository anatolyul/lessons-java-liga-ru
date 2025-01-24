package ru.hofftech.consolepackages.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.consolepackages.model.Command;
import ru.hofftech.consolepackages.model.enums.Argument;
import ru.hofftech.consolepackages.repository.BoxRepository;
import ru.hofftech.consolepackages.service.CommandExecutor;

import java.util.Map;

/**
 * Сервис для выполнения команды удаления коробки.
 */
@Service
@RequiredArgsConstructor
public class BoxDeleteCommandService implements CommandExecutor {
    private final BoxRepository boxRepository;

    /**
     * Выполняет команду удаления коробки.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(Command command) {
        Map<Argument, String> arguments = command.arguments();
        return boxRepository.deleteBox(arguments.get(Argument.NAME));
    }
}
