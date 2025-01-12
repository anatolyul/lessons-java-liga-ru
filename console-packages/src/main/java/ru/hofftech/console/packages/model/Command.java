package ru.hofftech.console.packages.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Command {
    private ConsoleCommand command;
    private List<CommandArgument> arguments;
}
