package ru.hofftech.logistictelegrambotservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.hofftech.logistictelegrambotservice.config.TelegramBotConfig;
import ru.hofftech.logistictelegrambotservice.service.handler.CommandHandler;


/**
 * Контроллер для обработки команд, полученных через Telegram.
 */
@Slf4j
@Component
public class TelegramBotController extends TelegramLongPollingBot {

    private final CommandHandler commandHandler;
    private final TelegramBotConfig telegramBotConfig;

    /**
     * Конструктор для инициализации Telegram бота.
     *
     * @param commandHandler    обработчик команд
     * @param telegramBotConfig конфигурация Telegram бота
     */
    @Autowired
    public TelegramBotController(CommandHandler commandHandler, TelegramBotConfig telegramBotConfig) {
        super(telegramBotConfig.getToken());
        this.commandHandler = commandHandler;
        this.telegramBotConfig = telegramBotConfig;
    }

    /**
     * Возвращает имя пользователя бота.
     *
     * @return имя пользователя бота
     */
    @Override
    public String getBotUsername() {
        return telegramBotConfig.getUsername();
    }

    /**
     * Обрабатывает обновления, полученные от Telegram.
     *
     * @param update обновление от Telegram
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.equals("/start") || messageText.equals("/help")) {
                sendMessage(chatId, commandHandler.handle("help"));
            } else {
                sendMessage(chatId, commandHandler.handle(messageText));
            }
        }
    }

    /**
     * Отправляет сообщение в Telegram.
     *
     * @param chatId идентификатор чата
     * @param text   текст сообщения
     */
    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.enableHtml(true);
        message.setText(text);
        message.setText("<pre>" + text + "</pre>");

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки в Telegram", e);
        }
    }
}
