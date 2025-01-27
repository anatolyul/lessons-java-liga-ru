package ru.hofftech.logisticservice.model;

import ru.hofftech.logisticservice.model.enums.Argument;
import ru.hofftech.logisticservice.model.enums.ConsoleCommand;

import java.util.Map;

/**
 * Модель команды, содержащей консольную команду и список аргументов.
 */
public record Command(ConsoleCommand consoleCommand, Map<Argument, String> arguments) {
}
