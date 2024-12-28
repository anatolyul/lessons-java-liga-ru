package ru.hofftech.console.packages.model.converter;

import ru.hofftech.console.packages.model.enums.ConsoleCommand;

import java.util.regex.Pattern;

public class ConsoleCommandConverter {
    public ConsoleCommand convertStringToEnum(String consoleCommand) {
        for (ConsoleCommand command : ConsoleCommand.values()) {
            Pattern pattern = Pattern.compile(command.getConsoleCommand());
            if (pattern.matcher(consoleCommand).matches()) {
                return command;
            }
        }
        return ConsoleCommand.UNKNOWN;
    }
}
