package ru.hofftech.console.packages.util;

import ru.hofftech.console.packages.model.Box;

import java.util.List;

public interface ParserBoxes {
    List<Box> parse(String filePath);
}
