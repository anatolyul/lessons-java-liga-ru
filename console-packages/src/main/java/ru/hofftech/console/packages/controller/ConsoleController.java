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
                
                Справочник команд:
                exit - завершение работы
                
                Примеры команд для работы с посылкой:
                Создание
                create -name "Квадратное колесо" -form "xxx\\nx x\\nxxx" -symbol "o"
                Поиск и получение информации
                find -name "Квадратное колесо"
                Редактирование
                edit -id "Квадратное колесо" -name "КУБ" -form "xxx\\nxxx\\nxxx" -symbol "%"
                Удаление
                delete -name "Посылка Тип 4"
                
                Примеры команд для погрузки в машины:
                Загрузка посылок по имени из параметра -parcels-text в машины с размерами параметра -trucks
                load -parcels-text "Посылка Тип 1\\nПосылка Тип 4\\nКУБ" -trucks "3x3\\n3x3\\n6x2" -type "one2one"
                Аналогично с сохранением результатов в файл
                load -parcels-text "Посылка Тип 1\\nПосылка Тип 4\\nКУБ" -trucks "3x3\\n3x3\\n6x2" -type "one2one" -out-filename "trucks.json"
                Аналогично, только имена посылок берем из файла указанного в параметре -parcels-file
                load -parcels-file "parcels.csv" -trucks "3x3\\n3x3\\n6x2" -type "one2one"
                load -parcels-file "parcels.csv" -trucks "3x3\\n3x3\\n6x2" -type "one2one" -out-filename "trucks.json"
                
                Примеры команд для разгрузки машин:
                Загрузка данных по машинам из файла переданным в параметре -in-filename и выгрузка результатов
                unload -in-filename "trucks.json"
                Аналогично результат выгружаем в файл указанный в параметре -out-filename
                unload -in-filename "trucks.json" -out-filename "parcels.csv"
                Аналогично, но ещё добавляем колонку с кол-вом
                unload -in-filename "trucks.json" -out-filename "parcels-with-count.csv" -withcount "true"
                
                Алгоритмы погрузки определяется параметром -type:
                one2one - простой (одна посылка = одна машина)
                max - сложный (максимальное размещение нескольких посылок по машинам)
                uniform - равномерная погрузка по машинам
                """);

        var scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String commandString = scanner.nextLine();
            String result = commandHandler.handle(commandString);
            System.out.println(result);
        }
    }
}
