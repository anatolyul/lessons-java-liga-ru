package ru.hofftech.console.packages.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.hofftech.console.packages.model.enums.Argument;

@Getter
@Setter
@AllArgsConstructor
public class CommandArgument {
    private Argument name;
    private String value;
}
