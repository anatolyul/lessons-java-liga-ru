package ru.hofftech.logisticcliservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticcliservice.dto.OrderDto;
import ru.hofftech.logisticcliservice.dto.command.BillingCommandDto;
import ru.hofftech.logisticcliservice.service.CommandExecutor;
import ru.hofftech.logisticcliservice.service.LogisticService;

import java.util.stream.Collectors;

/**
 * Сервис для выполнения команд биллинга.
 */
@Service
@RequiredArgsConstructor
public class BillingCommandService implements CommandExecutor<BillingCommandDto> {

    private final LogisticService logisticService;

    /**
     * Выполняет команду биллинга.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(BillingCommandDto command) {
        return logisticService.findOrdersByNameWithPeriod(
                        command.getUserName(),
                        command.getStartDate(),
                        command.getEndDate()).stream()
                .map(OrderDto::toString)
                .collect(Collectors.joining("\n"));
    }
}
