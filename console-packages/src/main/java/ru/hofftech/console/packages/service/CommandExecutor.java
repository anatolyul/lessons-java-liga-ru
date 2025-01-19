package ru.hofftech.console.packages.service;

import org.springframework.stereotype.Service;
import ru.hofftech.console.packages.model.Command;

@Service
public interface CommandExecutor {
    String execute(Command command);
}
