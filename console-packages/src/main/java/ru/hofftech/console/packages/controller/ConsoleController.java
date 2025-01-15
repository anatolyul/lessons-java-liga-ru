package ru.hofftech.console.packages.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.console.packages.service.handler.CommandHandler;

import java.util.Scanner;

/**
 * Контроллер для обработки команд, вводимых через консоль.
 */
@Slf4j
@RequiredArgsConstructor
public class ConsoleController {
    private final CommandHandler commandHandler;
    private final String helpText;

    /**
     * Запускает консольный интерфейс для ввода команд и обрабатывает их.
     */
    public void listen() {
        log.info(helpText);

        var scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String commandString = scanner.nextLine();
            String result = commandHandler.handle(commandString);
            System.out.println(result);
        }
    }
}
