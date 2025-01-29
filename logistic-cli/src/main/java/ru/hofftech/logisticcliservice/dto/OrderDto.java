package ru.hofftech.logisticcliservice.dto;

import lombok.Getter;
import lombok.Setter;
import ru.hofftech.logisticcliservice.dto.command.BaseCommandDto;
import ru.hofftech.logisticcliservice.enums.TypeOrderProcess;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class OrderDto implements BaseCommandDto {

    private Long id;
    private TypeOrderProcess type;
    private String clientName;
    private LocalDate date;
    private Long truckCount;
    private Long boxCount;
    private Long segmentCount;
    private BigDecimal amount;

    public String toString() {
        return String.format("%s; %s; %d машин; %d посылок; %.2f рублей",
                getDate(),
                getType().getCode(),
                getTruckCount(),
                getBoxCount(),
                getAmount());
    }
}
