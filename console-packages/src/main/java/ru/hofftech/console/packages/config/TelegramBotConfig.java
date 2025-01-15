package ru.hofftech.console.packages.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelegramBotConfig {
    private Bot bot;

    @Getter
    @Setter
    public static class Bot {
        private String username;
        private String token;
    }
}
