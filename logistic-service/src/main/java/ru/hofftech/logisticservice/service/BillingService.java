package ru.hofftech.logisticservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<OrderDto> findAll(Pageable pageable) {
        Page<OrderDto> orders = orderRepository.findAll(pageable).map(orderMapper::toDto);
        orders.forEach(order -> order.setAmount(calculatedAmount(order)));
        return orders;
    }

    public List<OrderDto> findByNameWithPeriod(String clientName,
                                               LocalDate startDate,
                                               LocalDate endDate) {
        List<OrderDto> orders = orderMapper.toDtoList(
                orderRepository.findAllByClientNameAndDateBetween(clientName, startDate, endDate));
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
