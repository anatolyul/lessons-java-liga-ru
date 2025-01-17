package ru.hofftech.console.packages.service.command;

import ru.hofftech.console.packages.model.Command;
import ru.hofftech.console.packages.service.CommandExecutor;

public class ExitCommandService implements CommandExecutor {
    /**
     * @param command
     * @return
     */
    @Override
    public String execute(Command command) {
        System.exit(0);
        return null;
    }
}
