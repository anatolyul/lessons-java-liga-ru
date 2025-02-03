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

    private static final Pattern commandPattern = Pattern.compile("(\\w+)(.*)");
    private static final Pattern argPattern = Pattern.compile("(-[\\w-]+|--[\\w-]+)\\s+\"([^\"]+)\"");
    private static final int COMMAND_GROUP = 1;
    private static final int ARGS_GROUP = 2;
    private static final int ARG_KEY_GROUP = 1;
    private static final int ARG_VALUE_GROUP = 2;

    /**
     * Парсит строку команды и аргументов в объект Command.
     *
     * @param consoleCommand строка команды и аргументов
     * @return объект Command, содержащий команду и список аргументов
     */
    public CommandDto parseCommandArgs(String consoleCommand) {
        ConsoleCommand consoleCommandResult = ConsoleCommand.convertStringToEnum(consoleCommand);
        Matcher commandMatcher = commandPattern.matcher(consoleCommand);

        Map<Argument, String> args = new EnumMap<>(Argument.class);
        if (commandMatcher.find()) {
            if (consoleCommandResult == ConsoleCommand.UNKNOWN) {
                consoleCommandResult = ConsoleCommand
                        .convertStringToEnum(commandMatcher.group(COMMAND_GROUP));
            }

            String argsString = commandMatcher.group(ARGS_GROUP)
                    .trim().replace("\\n", "\n");
            Matcher argMatcher = argPattern.matcher(argsString);

            while (argMatcher.find()) {
                Argument arg = Argument.convertStringToEnum(argMatcher.group(ARG_KEY_GROUP));
                args.put(arg, argMatcher.group(ARG_VALUE_GROUP));
            }
        }

        return CommandDto.builder()
                .consoleCommand(consoleCommandResult)
                .arguments(args)
                .build();
    }

}
