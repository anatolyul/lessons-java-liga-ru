package ru.hofftech.console.packages.service.command;

import lombok.RequiredArgsConstructor;
import ru.hofftech.console.packages.model.Command;
import ru.hofftech.console.packages.model.enums.Argument;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.service.CommandExecutor;

import java.util.Map;

@RequiredArgsConstructor
public class BoxDeleteCommandService implements CommandExecutor {
    private final BoxRepository boxRepository;

    /**
     * @param command
     * @return
     */
    @Override
    public String execute(Command command) {
        Map<Argument, String> arguments = command.getArguments();
        return boxRepository.deleteBox(arguments.get(Argument.NAME));
    }
}
