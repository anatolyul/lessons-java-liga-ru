package ru.hofftech.model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public record Box(int width, int height, String content) {
    public boolean isValid() {
        if (this.width * this.height != Integer.parseInt(this.content)) {
            log.warn("Skip Box {} not valid {} x {}",
                    this.content,
                    this.height,
                    this.width);
            return false;
        } else {
            return true;
        }
    }
}
