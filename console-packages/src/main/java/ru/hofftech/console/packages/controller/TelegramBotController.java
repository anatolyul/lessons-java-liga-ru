package ru.hofftech.console.packages.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.hofftech.console.packages.service.handler.CommandHandler;

@Slf4j
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

            if (messageText.equals("/start") || messageText.equals("/help")) {
                sendMessage(chatId, """
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