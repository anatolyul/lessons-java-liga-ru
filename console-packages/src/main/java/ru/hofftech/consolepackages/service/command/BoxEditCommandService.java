package ru.hofftech.consolepackages.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.consolepackages.model.Command;
import ru.hofftech.consolepackages.model.enums.Argument;
import ru.hofftech.consolepackages.repository.BoxRepository;
import ru.hofftech.consolepackages.service.CommandExecutor;

import java.util.Map;

/**
 * Сервис для выполнения команды редактирования коробки.
 */
@Service
@RequiredArgsConstructor
public class BoxEditCommandService implements CommandExecutor {
    private final BoxRepository boxRepository;

    /**
     * Выполняет команду редактирования коробки.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(Command command) {
        Map<Argument, String> arguments = command.arguments();
        return boxRepository.updateBox(arguments.get(Argument.ID),
                arguments.get(Argument.NAME),
                arguments.get(Argument.FORM),
                arguments.get(Argument.SYMBOL));
    }
}
