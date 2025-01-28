package ru.hofftech.logistictelegrambotservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoxDto {

    private Long id;
    private String name;
    private String symbol;
    private String form;

    public void setForm(String form) {
        this.form = form
                .replace("n", "\n")
                .replace("\\n", "\n");
    }

    public String toString() {
        return "Посылка:\nname: " + this.getName() + "\nform:\n" + this.getForm().replace("x", symbol);
    }
}
