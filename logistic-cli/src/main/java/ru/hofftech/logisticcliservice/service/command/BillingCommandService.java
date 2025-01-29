package ru.hofftech.logisticcliservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticcliservice.dto.OrderDto;
import ru.hofftech.logisticcliservice.dto.command.BaseCommandDto;
import ru.hofftech.logisticcliservice.dto.command.BillingCommandDto;
import ru.hofftech.logisticcliservice.service.CommandExecutor;
import ru.hofftech.logisticcliservice.service.LogisticService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для выполнения команд биллинга.
 */
@Service
@RequiredArgsConstructor
public class BillingCommandService implements CommandExecutor {

    private final LogisticService logisticService;

    /**
     * Выполняет команду биллинга.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(BaseCommandDto command) {
        BillingCommandDto billingCommandDto =  (BillingCommandDto) command;
        List<OrderDto> orders = logisticService.findOrdersByNameWithPeriod(
                billingCommandDto.getUserName(),
                billingCommandDto.getStartDate(),
                billingCommandDto.getEndDate());
        return orders.stream()
                .map(OrderDto::toString)
                .collect(Collectors.joining("\n"));
    }
}
