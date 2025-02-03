package ru.hofftech.logisticservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hofftech.logisticservice.dto.OrderDto;
import ru.hofftech.logisticservice.service.BillingService;

import java.time.LocalDate;
import java.util.List;

/**
 * Контроллер для управления биллингом.
 */
@RestController
@RequestMapping("/api/v1/billing")
@RequiredArgsConstructor
@Tag(name = "Billing", description = "Данный по операциям клиентов")
public class BillingController {

    private final BillingService billingService;

    /**
     * Получает список всех заказов с пагинацией.
     *
     * @param pageable параметры пагинации
     * @return список всех заказов
     */
    @Operation(summary = "Получить список всех заказов с пагинацией",
            description = "Возвращает список всех доступных заказов с пагинацией.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список заказов успешно получен",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content)})
    @GetMapping
    public Page<OrderDto> findAll(Pageable pageable) {
        return billingService.findAll(pageable);
    }

    /**
     * Получает заказы по имени клиента и периоду.
     *
     * @param clientName имя клиента
     * @param startDate  начальная дата периода
     * @param endDate    конечная дата периода
     * @return список заказов, соответствующих критериям
     */
    @Operation(summary = "Получить заказы по имени клиента и периоду",
            description = "Возвращает список заказов, соответствующих указанному имени клиента и периоду.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список заказов успешно получен",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверные параметры запроса",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content)})
    @GetMapping("/find")
    public List<OrderDto> findByNameWithPeriod(@RequestParam String clientName,
                                               @RequestParam LocalDate startDate,
                                               @RequestParam LocalDate endDate) {
        return billingService.findByNameWithPeriod(clientName, startDate, endDate);
    }
}
