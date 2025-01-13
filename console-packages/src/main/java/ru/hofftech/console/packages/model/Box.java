package ru.hofftech.console.packages.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
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

    @JsonIgnore
    private String form;

    @JsonProperty("coordinates")
    private boolean[][] formCoordinates;

    public Box() {}

    public Box(int width, int height, String symbol) {
        this.width = width;
        this.height = height;
        this.symbol = symbol;
    }

    public void setForm(String form) {
        this.form = form
                .replace("x", symbol)
                .replace("\\n", "\n");
    }

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

    public void TruckPosition(int startHeight, int startWidth) {
        this.startHeight = startHeight;
        this.startWidth = startWidth;
    }

    @JsonIgnore
    public boolean isValid() {
        return this.width * this.height == Integer.parseInt(this.symbol);
    }
}