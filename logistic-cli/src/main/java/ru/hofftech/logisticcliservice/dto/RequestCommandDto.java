package ru.hofftech.logisticcliservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.hofftech.logisticcliservice.enums.Argument;
import ru.hofftech.logisticcliservice.enums.ConsoleCommand;

import java.util.Map;

/**
 * Модель команды, содержащей консольную команду и список аргументов.
 */
@Getter
@AllArgsConstructor
public class RequestCommandDto {
    private ConsoleCommand consoleCommand;
    private Map<Argument, String> arguments;
}
