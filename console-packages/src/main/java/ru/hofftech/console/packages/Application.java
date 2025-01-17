package ru.hofftech.console.packages;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.hofftech.console.packages.config.TelegramBotConfig;
import ru.hofftech.console.packages.controller.ConsoleController;
import ru.hofftech.console.packages.controller.TelegramBotController;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.service.FileWriterService;
import ru.hofftech.console.packages.service.FormatterService;
import ru.hofftech.console.packages.service.ResultOutSaveService;
import ru.hofftech.console.packages.service.UnloaderTrucksToBoxesService;
import ru.hofftech.console.packages.service.converter.CommandArgConverterService;
import ru.hofftech.console.packages.service.factory.CommandExecutorFactory;
import ru.hofftech.console.packages.service.factory.LoaderBoxesInTrucksServiceFactory;
import ru.hofftech.console.packages.service.factory.ParserBoxesServiceFactory;
import ru.hofftech.console.packages.service.handler.CommandHandler;
import ru.hofftech.console.packages.util.YamlConfigLoader;

/**
 * Главный класс приложения для управления коробками и грузовиками.
 */
public class Application {

    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        String HELP_TEXT = """
                
                Справочник команд:
                exit - завершение работы
                
                Примеры команд для работы с посылкой:
                Список всех посылок
                list
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
                """;

        CommandHandler commandHandler = initializeCommandHandler();

        try {
            TelegramBotConfig telegramBotConfig = YamlConfigLoader.loadConfig(
                    "application.yml",
                    TelegramBotConfig.class);
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBotController(commandHandler, telegramBotConfig, HELP_TEXT));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        ConsoleController consoleController = new ConsoleController(commandHandler, HELP_TEXT);
        consoleController.listen();
    }

    /**
     * Инициализирует обработчик команд.
     *
     * @return инициализированный обработчик команд
     */
    private static CommandHandler initializeCommandHandler() {
        CommandArgConverterService commandArgConverterService = new CommandArgConverterService();

        return new CommandHandler(
                commandArgConverterService,
                new CommandExecutorFactory(
                        commandArgConverterService,
                        new FormatterService(),
                        new ParserBoxesServiceFactory(),
                        new LoaderBoxesInTrucksServiceFactory(),
                        new UnloaderTrucksToBoxesService(),
                        new BoxRepository(),
                        new ResultOutSaveService(new FileWriterService()))
        );
    }
}
