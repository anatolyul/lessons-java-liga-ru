package ru.hofftech.logistictelegrambotservice.dto;

import lombok.Builder;
import lombok.Getter;
import ru.hofftech.logistictelegrambotservice.enums.Argument;
import ru.hofftech.logistictelegrambotservice.enums.ConsoleCommand;

import java.util.Map;

/**
 * Модель команды, содержащей консольную команду и список аргументов.
 */
@Getter
@Builder
public class CommandDto {
    private ConsoleCommand consoleCommand;
    private Map<Argument, String> arguments;
}
