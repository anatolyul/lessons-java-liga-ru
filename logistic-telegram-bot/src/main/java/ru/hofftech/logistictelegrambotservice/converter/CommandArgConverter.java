package ru.hofftech.logistictelegrambotservice.converter;

import org.springframework.stereotype.Component;
import ru.hofftech.logistictelegrambotservice.dto.CommandDto;
import ru.hofftech.logistictelegrambotservice.enums.Argument;
import ru.hofftech.logistictelegrambotservice.enums.ConsoleCommand;

import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Конвертер для преобразования строковых представлений команд и аргументов в соответствующие перечисления.
 */
@Component
public class CommandArgConverter {

    /**
     * Парсит строку команды и аргументов в объект Command.
     *
     * @param consoleCommand строка команды и аргументов
     * @return объект Command, содержащий команду и список аргументов
     */
    public CommandDto parseCommandArgs(String consoleCommand) {
        ConsoleCommand consoleCommandResult = ConsoleCommand.convertStringToEnum(consoleCommand);

        // Регулярное выражение для извлечения команды и аргументов
        String commandRegex = "(\\w+)(.*)";
        Pattern commandPattern = Pattern.compile(commandRegex);
        Matcher commandMatcher = commandPattern.matcher(consoleCommand);

        Map<Argument, String> args = new EnumMap<>(Argument.class);
        if (commandMatcher.find()) {
            if (consoleCommandResult == ConsoleCommand.UNKNOWN) {
                consoleCommandResult = ConsoleCommand.convertStringToEnum(commandMatcher.group(1));
            }

            String argsString = commandMatcher.group(2).trim().replace("\\n", "\n");

            // Регулярное выражение для извлечения пар "ключ-значение"
            String argRegex = "(-[\\w-]+|--[\\w-]+)\\s+\"([^\"]+)\"";
            Pattern argPattern = Pattern.compile(argRegex);
            Matcher argMatcher = argPattern.matcher(argsString);

            while (argMatcher.find()) {
                Argument arg = Argument.convertStringToEnum(argMatcher.group(1));
                args.put(arg, argMatcher.group(2));
            }
        }

        return CommandDto.builder()
                .consoleCommand(consoleCommandResult)
                .arguments(args)
                .build();
    }

}
