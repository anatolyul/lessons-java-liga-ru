package ru.hofftech.consolepackages.model;

import ru.hofftech.consolepackages.model.enums.Argument;
import ru.hofftech.consolepackages.model.enums.ConsoleCommand;

import java.util.Map;

/**
 * Модель команды, содержащей консольную команду и список аргументов.
 */
public record Command(ConsoleCommand consoleCommand, Map<Argument, String> arguments) {
}
