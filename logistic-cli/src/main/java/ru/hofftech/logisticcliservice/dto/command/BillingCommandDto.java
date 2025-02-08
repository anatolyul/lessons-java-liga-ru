package ru.hofftech.logisticcliservice.dto.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class BillingCommandDto implements BaseCommandDto {

    private String userName;
    private LocalDate startDate;
    private LocalDate endDate;

}
