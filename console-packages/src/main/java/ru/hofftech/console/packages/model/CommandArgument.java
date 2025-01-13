package ru.hofftech.console.packages.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.hofftech.console.packages.model.enums.Argument;

/**
 * Модель аргумента команды.
 */
@Getter
@Setter
@AllArgsConstructor
public class CommandArgument {
    /**
     * Имя аргумента.
     */
    private Argument name;

    /**
     * Значение аргумента.
     */
    private String value;
}
