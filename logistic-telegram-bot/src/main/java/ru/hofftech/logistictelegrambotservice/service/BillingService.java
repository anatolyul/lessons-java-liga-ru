package ru.hofftech.logistictelegrambotservice.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import ru.hofftech.logistictelegrambotservice.dto.OrderDto;

import java.time.LocalDate;
import java.util.List;

public interface BillingService {

    /**
     * Получает заказы по имени клиента и периоду.
     *
     * @param clientName имя клиента
     * @param startDate  начальная дата периода
     * @param endDate    конечная дата периода
     * @return список заказов, соответствующих критериям
     */
    @GetExchange("/api/v1/billing/find")
    List<OrderDto> findOrdersByNameWithPeriod(@RequestParam String clientName,
                                              @RequestParam LocalDate startDate,
                                              @RequestParam LocalDate endDate);
}
