package ru.hofftech.console.packages.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import ru.hofftech.console.packages.service.handler.CommandHandler;

import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {
    private final CommandHandler commandHandler;

    public void listen() {
        log.info("""
                
                Старт приложения Консольные посылки...

                Справочник команд:
                import boxes.txt - загрузка файла с посылками
                import trucks.json - загрузка файла с грузовиками
                limit 5 - максимальное кол-во машин для погрузки (по умолчанию 1)
                exit - завершение работы
                
                Выбор алгоритма погрузки:
                1 - простой (одна посылка = одна машина)
                2 - сложный (оптимальное размещение нескольких посылок по машинам)
                3 - равномерная погрузка по машинам
                """);

        var scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String commandString = scanner.nextLine();
            commandHandler.handle(commandString);
        }
    }
}
