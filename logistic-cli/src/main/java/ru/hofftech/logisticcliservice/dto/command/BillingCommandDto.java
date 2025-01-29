package ru.hofftech.logisticcliservice.dto.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@Builder
public class BillingCommandDto implements BaseCommandDto {

    private String userName;
    private LocalDate startDate;
    private LocalDate endDate;

    public LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }

    public void setStringStartDate(String startDate) {
        this.startDate = stringToLocalDate(startDate);
    }

    public void setStringEndDate(String endDate) {
        this.endDate = stringToLocalDate(endDate);
    }
}
