package ru.hofftech.logisticservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Главный класс приложения для управления коробками и грузовиками.
 */
@SpringBootApplication
@EnableScheduling
public class LogisticServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticServiceApplication.class, args);
    }
}
