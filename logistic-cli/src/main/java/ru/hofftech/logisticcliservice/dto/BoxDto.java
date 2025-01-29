package ru.hofftech.logisticcliservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoxDto {

    private String name;
    private String symbol;
    private String form;

    public void setForm(String form) {
        this.form = form
                .replace("\\", "")
                .replace("n", "\n");
    }

    public String toString() {
        return "Посылка:\nname: " + this.getName() + "\nform:\n" + this.getForm().replace("x", symbol);
    }
}
