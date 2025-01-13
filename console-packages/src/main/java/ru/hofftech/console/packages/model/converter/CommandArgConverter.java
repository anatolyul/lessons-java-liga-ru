package ru.hofftech.console.packages.model.converter;

import ru.hofftech.console.packages.model.Command;
import ru.hofftech.console.packages.model.CommandArgument;
import ru.hofftech.console.packages.model.enums.Argument;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;
import ru.hofftech.console.packages.model.enums.TypeAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Конвертер для преобразования строковых представлений команд и аргументов в соответствующие перечисления.
 */
public class CommandArgConverter {

    /**
     * Преобразует строковое представление консольной команды в перечисление ConsoleCommand.
     *
     * @param consoleCommand строковое представление консольной команды
     * @return перечисление ConsoleCommand, соответствующее строковому представлению
     */
    public ConsoleCommand convertCommandStringToEnum(String consoleCommand) {
        for (ConsoleCommand command : ConsoleCommand.values()) {
            Pattern pattern = Pattern.compile(command.getConsoleCommand());
            if (pattern.matcher(consoleCommand).matches()) {
                return command;
            }
        }
        return ConsoleCommand.UNKNOWN;
    }

    /**
     * Преобразует строковое представление аргумента команды в перечисление Argument.
     *
     * @param argumentCode строковое представление аргумента команды
     * @return перечисление Argument, соответствующее строковому представлению
     */
    public Argument convertArgumentStringToEnum(String argumentCode) {
        for (Argument argument : Argument.values()) {
            Pattern pattern = Pattern.compile(argument.getArgument());
            if (pattern.matcher(argumentCode).matches()) {
                return argument;
            }
        }
        return Argument.UNKNOWN;
    }

    /**
     * Преобразует строковое представление типа алгоритма в перечисление TypeAlgorithm.
     *
     * @param typeAlgorithm строковое представление типа алгоритма
     * @return перечисление TypeAlgorithm, соответствующее строковому представлению
     */
    public TypeAlgorithm convertTypeAlgorithmStringToEnum(String typeAlgorithm) {
        for (TypeAlgorithm algorithm : TypeAlgorithm.values()) {
            Pattern pattern = Pattern.compile(algorithm.getCode());
            if (pattern.matcher(typeAlgorithm).matches()) {
                return algorithm;
            }
        }
        return null;
    }

    /**
     * Получает значение аргумента команды.
     *
     * @param arguments список аргументов команды
     * @param argument  аргумент команды
     * @return значение аргумента команды или null, если аргумент не найден
     */
    public String getArgumentValue(List<CommandArgument> arguments, Argument argument) {
        String result = arguments.stream()
                .filter(x -> x.getName() == argument)
                .map(CommandArgument::getValue)
                .findFirst().orElse(null);

        if (result != null) {
            result = result.trim();
            result = result.replace("\\n", "\n");
        }

        return result;
    }

    /**
     * Парсит строку команды и аргументов в объект Command.
     *
     * @param consoleCommand строка команды и аргументов
     * @return объект Command, содержащий команду и список аргументов
     */
    public Command parseCommandArgs(String consoleCommand) {
        Command command = new Command(ConsoleCommand.UNKNOWN, null);

        // Регулярное выражение для извлечения команды и аргументов
        String commandRegex = "(\\w+)(.*)";
        Pattern commandPattern = Pattern.compile(commandRegex);
        Matcher commandMatcher = commandPattern.matcher(consoleCommand);

        if (commandMatcher.find()) {
            command.setCommand(convertCommandStringToEnum(commandMatcher.group(1)));

            String argsString = commandMatcher.group(2).trim();

            // Регулярное выражение для извлечения пар "ключ-значение"
            String argRegex = "(-[\\w-]+|--[\\w-]+)\\s+\"([^\"]+)\"";
            Pattern argPattern = Pattern.compile(argRegex);
            Matcher argMatcher = argPattern.matcher(argsString);

            List<CommandArgument> args = new ArrayList<>();
            while (argMatcher.find()) {
                Argument arg = convertArgumentStringToEnum(argMatcher.group(1));
                args.add(new CommandArgument(arg, argMatcher.group(2)));
            }
            command.setArguments(args);
        }

        return command;
    }
}
