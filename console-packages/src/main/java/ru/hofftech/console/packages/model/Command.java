package ru.hofftech.console.packages.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.hofftech.console.packages.model.enums.Argument;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;

import java.util.Map;

/**
 * Модель команды, содержащей консольную команду и список аргументов.
 */
@Setter
@Getter
@AllArgsConstructor
public class Command {
    /**
     * Консольная команда.
     */
    private ConsoleCommand command;

    /**
     * Список аргументов команды.
     */
    private Map<Argument, String> arguments;
}
