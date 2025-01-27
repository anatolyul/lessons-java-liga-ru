package ru.hofftech.logisticservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.config.BillingConfig;
import ru.hofftech.logisticservice.dto.OrderDto;
import ru.hofftech.logisticservice.mapper.OrderMapper;
import ru.hofftech.logisticservice.model.enums.TypeOrderProcess;
import ru.hofftech.logisticservice.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillingService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BillingConfig billingConfig;

    public List<OrderDto> findAll() {
        List<OrderDto> orders = orderMapper.toDtoList(orderRepository.findAll());
        orders.forEach(order -> order.setAmount(calculatedAmount(order)));

        return orders;
    }

    public List<OrderDto> findByNameWithPeriod(String clientName,
                                               LocalDate startDate,
                                               LocalDate endDate) {
        List<OrderDto> orders = orderMapper.toDtoList(
                orderRepository.findByNameWithPeriod(clientName, startDate, endDate));
        orders.forEach(order -> order.setAmount(calculatedAmount(order)));

        return orders;
    }

    private BigDecimal calculatedAmount(OrderDto orderDto) {
        return BigDecimal.valueOf(
                orderDto.getSegmentCount() *
                        (orderDto.getType() == TypeOrderProcess.LOAD
                                ? billingConfig.getCostLoad()
                                : billingConfig.getCostUnload()));
    }
}
