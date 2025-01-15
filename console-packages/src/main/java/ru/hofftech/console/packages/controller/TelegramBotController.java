package ru.hofftech.console.packages.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.hofftech.console.packages.config.TelegramBotConfig;
import ru.hofftech.console.packages.service.handler.CommandHandler;

/**
 * Контроллер для обработки команд, полученных через Telegram.
 */
@Slf4j
@RequiredArgsConstructor
public class TelegramBotController extends TelegramLongPollingBot {
    private final CommandHandler commandHandler;
    private final TelegramBotConfig telegramBotConfig;
    private final String helpText;

    /**
     * Возвращает имя пользователя бота.
     *
     * @return имя пользователя бота
     */
    @Override
    public String getBotUsername() {
        return telegramBotConfig.getBot().getUsername();
    }

    /**
     * Возвращает токен бота.
     *
     * @return токен бота
     */
    @Override
    public String getBotToken() {
        return telegramBotConfig.getBot().getToken();
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
                sendMessage(chatId, helpText);
            } else {
                String result = commandHandler.handle(messageText);
                sendMessage(chatId, result);
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
