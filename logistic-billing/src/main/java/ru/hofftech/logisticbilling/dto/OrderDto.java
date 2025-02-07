package ru.hofftech.logisticbilling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.hofftech.logisticbilling.model.enums.TypeOrderProcess;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Биллинг история заказов")
public class OrderDto {

    @Schema(description = "Номер заказа")
    private Long id;

    @Schema(description = "Тип операции (Погрузка/Разгрузка)")
    private TypeOrderProcess type;

    @Schema(description = "Имя клиента")
    private String clientName;

    @Schema(description = "Дата операции")
    private LocalDate date;

    @Schema(description = "Кол-во машин")
    private Long truckCount;

    @Schema(description = "Кол-во коробок-посылок")
    private Long boxCount;

    @Schema(description = "Кол-во сегментов для расчета")
    private Long segmentCount;

    @Schema(description = "Стоимость")
    private BigDecimal amount;

}
