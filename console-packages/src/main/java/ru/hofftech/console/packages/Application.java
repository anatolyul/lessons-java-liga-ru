package ru.hofftech.console.packages;

import ru.hofftech.console.packages.controller.ConsoleController;
import ru.hofftech.console.packages.model.converter.ConsoleCommandConverter;
import ru.hofftech.console.packages.service.FileWriterService;
import ru.hofftech.console.packages.service.FormatterService;

public class Application {
    public static void main(String[] args) {
        ConsoleController consoleController = new ConsoleController(
                                                new ConsoleCommandConverter(),
                                                new FileWriterService(),
                                                new FormatterService());
        consoleController.listen();
    }

}