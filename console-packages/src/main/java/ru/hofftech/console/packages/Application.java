package ru.hofftech.console.packages;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.hofftech.console.packages.controller.ConsoleController;
import ru.hofftech.console.packages.controller.TelegramBotController;
import ru.hofftech.console.packages.model.converter.CommandArgConverter;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.service.FileWriterService;
import ru.hofftech.console.packages.service.FormatterService;
import ru.hofftech.console.packages.service.ResultOutSaveService;
import ru.hofftech.console.packages.service.UnloaderTrucksToBoxesService;
import ru.hofftech.console.packages.service.factory.LoaderBoxesInTrucksServiceFactory;
import ru.hofftech.console.packages.service.factory.ParserBoxesServiceFactory;
import ru.hofftech.console.packages.service.handler.CommandHandler;

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
        CommandHandler commandHandler = initializeCommandHandler();

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBotController(commandHandler));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        ConsoleController consoleController = new ConsoleController(commandHandler);
        consoleController.listen();
    }

    /**
     * Инициализирует обработчик команд.
     *
     * @return инициализированный обработчик команд
     */
    private static CommandHandler initializeCommandHandler() {
        return new CommandHandler(
                new CommandArgConverter(),
                new FormatterService(),
                new ParserBoxesServiceFactory(),
                new LoaderBoxesInTrucksServiceFactory(),
                new UnloaderTrucksToBoxesService(),
                new BoxRepository(),
                new ResultOutSaveService(new FileWriterService())
        );
    }
}
