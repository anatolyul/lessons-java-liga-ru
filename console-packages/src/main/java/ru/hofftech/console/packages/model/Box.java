package ru.hofftech.console.packages.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class Box {
    private int width;
    private int height;
    private int positionX;
    private int positionY;

    @JsonProperty("box")
    private String content;

    public Box() {}

    public Box(int width, int height, String content) {
        this.width = width;
        this.height = height;
        this.content = content;
    }

    public void TruckPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    @JsonIgnore
    public boolean isValid() {
        return this.width * this.height == Integer.parseInt(this.content);
    }
}
