package ru.hofftech.logistictelegrambotservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.hofftech.logistictelegrambotservice.controller.TelegramBotController;

/**
 * Конфигурационный класс для настроек Telegram бота.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegram.bot")
public class TelegramBotConfig {
    /**
     * Токен Telegram бота.
     */
    private String token;

    /**
     * Имя пользователя Telegram бота.
     */
    private String username;

    /**
     * Создает и регистрирует Telegram бота, если свойство "telegram.bot.enabled" установлено в "true".
     *
     * @param telegramBot контроллер Telegram бота
     * @return экземпляр TelegramBotsApi
     * @throws TelegramApiException если происходит ошибка при регистрации бота
     */
    @Bean
    @ConditionalOnProperty(name = "telegram.bot.enabled", havingValue = "true")
    public TelegramBotsApi telegramBotsApi(TelegramBotController telegramBot) throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(telegramBot);
        return api;
    }
}
