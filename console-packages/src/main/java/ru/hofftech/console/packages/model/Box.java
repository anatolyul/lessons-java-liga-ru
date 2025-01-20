package ru.hofftech.console.packages.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * Модель коробки, которая может быть загружена в грузовик.
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Box {

    @JsonIgnore
    private int width;

    @JsonIgnore
    private int height;

    @JsonIgnore
    private int startHeight;

    @JsonIgnore
    private int startWidth;

    @JsonProperty("type")
    private String symbol;

    @JsonProperty("name")
    private String name;

    @JsonProperty("form")
    private String form;

    @JsonProperty("coordinates")
    private boolean[][] formCoordinates;

    /**
     * Конструктор с шириной, высотой и символом.
     *
     * @param width  ширина коробки
     * @param height высота коробки
     * @param symbol символ коробки
     */
    public Box(int width, int height, String symbol) {
        this.width = width;
        this.height = height;
        this.symbol = symbol;
    }

    /**
     * Устанавливает форму коробки.
     *
     * @param form форма коробки
     */
    public void setForm(String form) {
        this.form = form
                .replace("x", symbol)
                .replace("n", "\n")
                .replace("\\n", "\n");
    }

    /**
     * Конструктор с именем, формой и символом.
     *
     * @param name   имя коробки
     * @param form   форма коробки
     * @param symbol символ коробки
     */
    public Box(String name, String form, String symbol) {
        setName(name);
        setSymbol(symbol);
        setForm(form);

        String[] lines = form.split("\n");
        this.width = Arrays.stream(lines).map(String::length).reduce(Integer::max).orElse(0);
        this.height = (int) Arrays.stream(lines).count();
        this.formCoordinates = new boolean[height][width];
        int posHeight = 0;
        int posWidth;
        for (String line : lines) {
            posWidth = 0;
            for (char c : line.toCharArray()) {
                if (!Character.isWhitespace(c)) {
                    this.formCoordinates[posHeight][posWidth] = true;
                }
                posWidth++;
            }
            posHeight++;
        }
    }

    /**
     * Устанавливает позицию коробки в грузовике.
     *
     * @param startHeight начальная высота для размещения
     * @param startWidth  начальная ширина для размещения
     */
    public void setStartPosition(int startHeight, int startWidth) {
        this.startHeight = startHeight;
        this.startWidth = startWidth;
    }

    /**
     * Вычисляет размер коробки на основе символа.
     *
     * @return размер коробки
     */
    public int calcSize() {
        int size = 0;
        for (char c : form.toCharArray()) {
            if (c == symbol.charAt(0)) {
                size++;
            }
        }
        return size;
    }

    /**
     * Проверяет, является ли коробка валидной.
     *
     * @return true, если коробка валидна, иначе false
     */
    @JsonIgnore
    public boolean isValid() {
        return this.width * this.height == Integer.parseInt(this.symbol);
    }
}
