package ru.hofftech.logisticservice.service.converter;

import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.model.enums.Argument;
import ru.hofftech.logisticservice.model.enums.ConsoleCommand;
import ru.hofftech.logisticservice.model.enums.TypeAlgorithm;
import ru.hofftech.logisticservice.service.FormatterService;

import java.io.File;
import java.util.Objects;
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
            result = Objects.requireNonNull(FormatterService.class.getClassLoader()
                    .getResource(fileName)).getPath();
        }
        return result;
    }
}
