package ru.hofftech.console.packages.model.converter;

import ru.hofftech.console.packages.model.ConsoleCommand;

public class ConsoleCommandConverter {
    public ConsoleCommand convertStringToEnum(String consoleCommand) {
        String[] commandResult = consoleCommand.split(" ");

        for (ConsoleCommand command : ConsoleCommand.values()) {
            if (command.getConsoleCommand().equalsIgnoreCase(commandResult[0])) {
                return command;
            }
        }
        return ConsoleCommand.UNKNOWN;
    }
}
