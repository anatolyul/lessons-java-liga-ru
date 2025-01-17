package ru.hofftech.console.packages.service;

import ru.hofftech.console.packages.model.Command;

public interface CommandExecutor {
    String execute(Command command);
}
