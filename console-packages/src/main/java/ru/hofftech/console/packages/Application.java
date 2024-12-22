package ru.hofftech.console.packages;

import ru.hofftech.console.packages.controller.ConsoleController;
import ru.hofftech.console.packages.model.converter.ConsoleCommandConverter;
import ru.hofftech.console.packages.util.ParserBoxes;

public class Application {
    public static void main(String[] args) {
        ConsoleController consoleController = new ConsoleController(
                                                new ParserBoxes(),
                                                new ConsoleCommandConverter());
        consoleController.listen();
    }

}