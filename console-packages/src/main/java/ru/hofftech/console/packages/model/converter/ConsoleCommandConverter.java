package ru.hofftech.console.packages.model.converter;

import ru.hofftech.console.packages.model.enums.ConsoleCommand;

public class ConsoleCommandConverter {
    public ConsoleCommand convertStringToEnum(String consoleCommand) {
        String[] commandResult = consoleCommand.split(" ");

        for (ConsoleCommand command : ConsoleCommand.values()) {
            if (command.getConsoleCommand().equalsIgnoreCase(commandResult[0])) {
                switch (command) {
                    case ConsoleCommand.IMPORT_FILE_TXT,
                         ConsoleCommand.IMPORT_FILE_JSON:
                        if (consoleCommand.endsWith(".txt")) return ConsoleCommand.IMPORT_FILE_TXT;
                        if (consoleCommand.endsWith(".json")) return ConsoleCommand.IMPORT_FILE_JSON;
                        return ConsoleCommand.UNKNOWN;
                    default:
                        return command;
                }
            }
        }
        return ConsoleCommand.UNKNOWN;
    }
}
