package ru.hofftech.consolepackages.service.converter;

import org.springframework.stereotype.Service;
import ru.hofftech.consolepackages.model.Command;
import ru.hofftech.consolepackages.model.enums.Argument;
import ru.hofftech.consolepackages.model.enums.ConsoleCommand;
import ru.hofftech.consolepackages.model.enums.TypeAlgorithm;
import ru.hofftech.consolepackages.service.FormatterService;

import java.io.File;
import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Конвертер для преобразования строковых представлений команд и аргументов в соответствующие перечисления.
 */
@Service
public class CommandArgConverterService {

    /**
     * Преобразует строковое представление консольной команды в перечисление ConsoleCommand.
     *
     * @param consoleCommand строковое представление консольной команды
     * @return перечисление ConsoleCommand, соответствующее строковому представлению
     */
    public ConsoleCommand convertCommandStringToEnum(String consoleCommand) {
        for (ConsoleCommand command : ConsoleCommand.values()) {
            Pattern pattern = Pattern.compile(command.getCode());
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
            Pattern pattern = Pattern.compile(argument.getCode());
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
        ConsoleCommand consoleCommandResult = convertCommandStringToEnum(consoleCommand);

        // Регулярное выражение для извлечения команды и аргументов
        String commandRegex = "(\\w+)(.*)";
        Pattern commandPattern = Pattern.compile(commandRegex);
        Matcher commandMatcher = commandPattern.matcher(consoleCommand);

        Map<Argument, String> args = new EnumMap<>(Argument.class);
        if (commandMatcher.find()) {
            if (consoleCommandResult == ConsoleCommand.UNKNOWN) {
                consoleCommandResult = convertCommandStringToEnum(commandMatcher.group(1));
            }

            String argsString = commandMatcher.group(2).trim().replace("\\n", "\n");

            // Регулярное выражение для извлечения пар "ключ-значение"
            String argRegex = "(-[\\w-]+|--[\\w-]+)\\s+\"([^\"]+)\"";
            Pattern argPattern = Pattern.compile(argRegex);
            Matcher argMatcher = argPattern.matcher(argsString);

            while (argMatcher.find()) {
                Argument arg = convertArgumentStringToEnum(argMatcher.group(1));
                args.put(arg, argMatcher.group(2));
            }
        }

        return new Command(consoleCommandResult, args);
    }

    /**
     * Преобразует имя файла в путь к файлу.
     *
     * @param fileName имя файла
     * @return путь к файлу
     */
    public String fileToPath(String fileName) {
        String result;
        if (new File(fileName).isFile()) {
            result = fileName;
        } else {
            result = FormatterService.class.getClassLoader()
                    .getResource(fileName).getPath();
        }
        return result;
    }
}
