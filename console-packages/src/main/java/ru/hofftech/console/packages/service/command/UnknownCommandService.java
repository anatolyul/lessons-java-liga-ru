package ru.hofftech.console.packages.service.command;

import org.springframework.stereotype.Service;
import ru.hofftech.console.packages.model.Command;
import ru.hofftech.console.packages.service.CommandExecutor;

@Service
public class UnknownCommandService implements CommandExecutor {
    /**
     * @param command
     * @return
     */
    @Override
    public String execute(Command command) {
        return "Приложение не поддерживает данную команду.";
    }
}
