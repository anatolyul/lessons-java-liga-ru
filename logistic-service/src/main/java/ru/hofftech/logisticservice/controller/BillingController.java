package ru.hofftech.logisticservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hofftech.logisticservice.dto.OrderDto;
import ru.hofftech.logisticservice.service.BillingService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/billing")
@RequiredArgsConstructor
public class BillingController {
    private final BillingService billingService;

    @GetMapping
    public List<OrderDto> findAll() {
        return billingService.findAll();
    }

    @GetMapping("/find")
    public List<OrderDto> findByNameWithPeriod(@RequestParam String clientName,
                                         @RequestParam LocalDate startDate,
                                         @RequestParam LocalDate endDate) {
        return billingService.findByNameWithPeriod(clientName, startDate, endDate);
    }
}
