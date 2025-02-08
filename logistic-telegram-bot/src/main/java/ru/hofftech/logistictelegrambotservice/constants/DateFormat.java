package ru.hofftech.logistictelegrambotservice.constants;

import java.time.format.DateTimeFormatter;

public final class DateFormat {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private DateFormat() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
