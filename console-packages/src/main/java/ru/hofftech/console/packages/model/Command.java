package ru.hofftech.console.packages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hofftech.console.packages.model.enums.Argument;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;

import java.util.Map;

/**
 * Модель команды, содержащей консольную команду и список аргументов.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Command {
    /**
     * Консольная команда.
     */
    private ConsoleCommand consoleCommand;

    /**
     * Список аргументов команды.
     */
    private Map<Argument, String> arguments;
}
