package ru.hofftech.console.packages.service.converter;

import ru.hofftech.console.packages.model.Command;
import ru.hofftech.console.packages.model.enums.Argument;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;
import ru.hofftech.console.packages.model.enums.TypeAlgorithm;
import ru.hofftech.console.packages.service.FormatterService;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Конвертер для преобразования строковых представлений команд и аргументов в соответствующие перечисления.
 */
public class CommandArgConverterService {

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
     * Парсит строку команды и аргументов в объект Command.
     *
     * @param consoleCommand строка команды и аргументов
     * @return объект Command, содержащий команду и список аргументов
     */
    public Command parseCommandArgs(String consoleCommand) {
        Command command = new Command(convertCommandStringToEnum(consoleCommand), null);

        if (command.getCommand() == ConsoleCommand.IMPORT_FILE_JSON
                || command.getCommand() == ConsoleCommand.IMPORT_FILE_TXT) {
            Map<Argument, String> args = new HashMap<>();
            args.put(Argument.IMPORT_FILENAME, FileNameCommandToPath(consoleCommand));
            command.setArguments(args);

            return command;
        }

        // Регулярное выражение для извлечения команды и аргументов
        String commandRegex = "(\\w+)(.*)";
        Pattern commandPattern = Pattern.compile(commandRegex);
        Matcher commandMatcher = commandPattern.matcher(consoleCommand);

        if (commandMatcher.find()) {
            command.setCommand(convertCommandStringToEnum(commandMatcher.group(1)));

            String argsString = commandMatcher.group(2).trim().replace("\\n", "\n");

            // Регулярное выражение для извлечения пар "ключ-значение"
            String argRegex = "(-[\\w-]+|--[\\w-]+)\\s+\"([^\"]+)\"";
            Pattern argPattern = Pattern.compile(argRegex);
            Matcher argMatcher = argPattern.matcher(argsString);

            Map<Argument, String> args = new HashMap<>();
            while (argMatcher.find()) {
                Argument arg = convertArgumentStringToEnum(argMatcher.group(1));
                args.put(arg, argMatcher.group(2));
            }


            command.setArguments(args);
        }

        return command;
    }

    /**
     * Преобразует команду импорта файла в путь к файлу.
     *
     * @param fileName команда импорта файла
     * @return путь к файлу
     */
    public String FileNameCommandToPath(String fileName) {
        final Pattern IMPORT_COMMAND_PATTERN = Pattern.compile("import (.+\\.(txt|json))");
        String result;
        Matcher matcher = IMPORT_COMMAND_PATTERN.matcher(fileName);
        fileName = matcher.matches() ? matcher.group(1) : fileName;
        result = FileToPath(fileName);
        return result;
    }

    /**
     * Преобразует имя файла в путь к файлу.
     *
     * @param fileName имя файла
     * @return путь к файлу
     */
    public String FileToPath(String fileName) {
        String result;
        if (new File(fileName).isFile()) {
            result = fileName;
        } else {
            result = Objects.requireNonNull(
                    FormatterService.class.getClassLoader()
                            .getResource(fileName)).getPath();
        }
        return result;
    }
}
