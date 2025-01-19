package ru.hofftech.console.packages.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "billing")
public class BillingConfig {
    private Double costLoad;
    private Double costUnload;
}
