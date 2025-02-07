package ru.hofftech.logisticservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hofftech.logisticservice.model.enums.TypeOrderProcess;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные по заказу")
public class OrderDto {

    @Schema(description = "Тип операции (Погрузка/Разгрузка)")
    private TypeOrderProcess type;

    @Schema(description = "Имя клиента")
    private String clientName;

    @Schema(description = "Дата операции")
    private LocalDate date;

    @Schema(description = "Кол-во машин")
    private Integer truckCount;

    @Schema(description = "Кол-во коробок-посылок")
    private Integer boxCount;

    @Schema(description = "Кол-во сегментов для расчета")
    private Integer segmentCount;
}
