package ru.hofftech.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Slf4j
public class Box {
    int width;
    int height;
    String content;

    public Box(int width, int height, String content) {
        this.width = width;
        this.height = height;
        this.content = content;
    }

    public boolean isValid() {
        if (this.width * this.height != Integer.parseInt(this.content)) {
            log.warn("Skip Box {} not valid {} x {}",
                            this.content,
                            this.height,
                            this.width);
            return false;
        }
        else {
            return true;
        }
    }
}
