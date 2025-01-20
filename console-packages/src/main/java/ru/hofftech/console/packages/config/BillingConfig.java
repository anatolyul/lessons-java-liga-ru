package ru.hofftech.console.packages.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс для настроек биллинга.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "billing")
public class BillingConfig {
    /**
     * Стоимость загрузки.
     */
    private Double costLoad;

    /**
     * Стоимость разгрузки.
     */
    private Double costUnload;
}
