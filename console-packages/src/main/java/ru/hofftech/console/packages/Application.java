package ru.hofftech.console.packages;

import ru.hofftech.console.packages.controller.ConsoleController;
import ru.hofftech.console.packages.model.converter.ConsoleCommandConverter;
import ru.hofftech.console.packages.service.FileWriterService;
import ru.hofftech.console.packages.service.FormatterService;
import ru.hofftech.console.packages.service.factory.LoaderBoxesInTrucksServiceFactory;
import ru.hofftech.console.packages.service.factory.ParserBoxesServiceFactory;
import ru.hofftech.console.packages.service.handler.CommandHandler;

public class Application {
    public static void main(String[] args) {
        ConsoleController consoleController = new ConsoleController(
                                                new CommandHandler(
                                                    new ConsoleCommandConverter(),
                                                    new FormatterService(),
                                                    new FileWriterService(),
                                                    new ParserBoxesServiceFactory(),
                                                    new LoaderBoxesInTrucksServiceFactory()));
        consoleController.listen();
    }

}