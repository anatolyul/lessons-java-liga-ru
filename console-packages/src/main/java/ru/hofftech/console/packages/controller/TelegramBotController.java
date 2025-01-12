package ru.hofftech.console.packages.controller;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.hofftech.console.packages.service.handler.CommandHandler;

@RequiredArgsConstructor
public class TelegramBotController extends TelegramLongPollingBot {
    private final CommandHandler commandHandler;

    @Override
    public String getBotUsername() {
        return "AppBoxInTruckBot";
    }

    @Override
    public String getBotToken() {
        return "7759796407:AAECD6Pa76zgxEAQtAxAFbu9BsQlPGNa918";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.equals("/start")) {
                sendMessage(chatId, """
                Справочник команд:
                limit 5 - максимальное кол-во машин для погрузки (по умолчанию 1)
                exit - завершение работы
                
                Выбор алгоритма погрузки:
                1 - простой (одна посылка = одна машина)
                2 - сложный (оптимальное размещение нескольких посылок по машинам)
                3 - равномерная погрузка по машинам
                
                Примеры команд:
                create -name "Квадратное колесо" -form "xxx\\nx x\\nxxx" -symbol "o"
                find -name "КУБ"
                edit -id "Квадратное колесо" -name "КУБ" -form "xxx\\nxxx\\nxxx" -symbol "%"
                delete -name "Посылка Тип 4"
                load -parcels-text "Посылка Тип 1\\nПосылка Тип 4\\nКУБ" -trucks "3x3\\n3x3\\n6x2" -type "Одна машина - Одна посылка" -out text
                load -parcels-file "parcels.csv" -trucks "3x3\\n3x3\\n6x2" -type "Одна машина - Одна посылка" -out json-file -out-filename "trucks.json"
                """);
            } else {
                String result = commandHandler.handle(messageText);
                sendMessage(chatId, result);
            }
        }
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}