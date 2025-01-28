package ru.hofftech.logistictelegrambotservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.hofftech.logistictelegrambotservice.enums.TypeOrderProcess;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class OrderDto {

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
