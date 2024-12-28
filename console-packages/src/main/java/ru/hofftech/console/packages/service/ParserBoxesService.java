package ru.hofftech.console.packages.service;

import ru.hofftech.console.packages.model.Box;

import java.util.List;

public interface ParserBoxesService {
    List<Box> parse(String filePath);
}
