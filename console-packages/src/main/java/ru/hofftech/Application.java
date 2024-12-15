package ru.hofftech;

import lombok.extern.slf4j.Slf4j;
import ru.hofftech.controller.ConsoleController;

@Slf4j
public class Application {
    public static void main(String[] args) {
        log.info("""
                
                Старт приложения Консольные посылки...
                """);
        ConsoleController consoleController = new ConsoleController();
        consoleController.listen();
    }

}