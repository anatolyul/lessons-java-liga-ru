package ru.hofftech.logisticservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Менеджер коробок-посылок")
public class BoxDto {

    @Schema(description = "Id коробки-посылки")
    private Long id;

    @Schema(description = "Наименование посылки")
    private String name;

    @Schema(description = "Символ для прорисовки формы")
    private String symbol;

    @Schema(description = "Форма посылки")
    private String form;

    @JsonIgnore
    private int width;

    @JsonIgnore
    private int height;

    @JsonProperty("coordinates")
    private boolean[][] formCoordinates;

    public void setForm(String form) {
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

        this.form = form
                .replace("n", "\n")
                .replace("\\n", "\n");
    }

    public BoxDto(String name, String form, String symbol) {
        setName(name);
        setSymbol(symbol);
        setForm(form);
    }

    public String toString() {
        return "Посылка:\nname: " + this.getName() + "\nform:\n" + this.getForm().replace("x", symbol);
    }
}
