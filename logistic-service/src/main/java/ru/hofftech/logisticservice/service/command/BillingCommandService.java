package ru.hofftech.logisticservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.OrderDto;
import ru.hofftech.logisticservice.model.Command;
import ru.hofftech.logisticservice.model.enums.Argument;
import ru.hofftech.logisticservice.service.BillingService;
import ru.hofftech.logisticservice.service.CommandExecutor;
import ru.hofftech.logisticservice.service.FormatterService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Сервис для выполнения команд биллинга.
 */
@Service
@RequiredArgsConstructor
public class BillingCommandService implements CommandExecutor {
    private final FormatterService formatterService;
    private final BillingService billingService;

    /**
     * Выполняет команду биллинга.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(Command command) {
        Map<Argument, String> arguments = command.arguments();
        String userId = arguments.get(Argument.USER);
        LocalDate periodFrom = formatterService
                .stringToLocalDate(arguments.get(Argument.PERIOD_FROM));
        LocalDate periodTo = formatterService
                .stringToLocalDate(arguments.get(Argument.PERIOD_TO));

        List<OrderDto> orders = billingService.findByNameWithPeriod(userId, periodFrom, periodTo);
        return orders.stream()
                .map(formatterService::formatOrderOperationToBillingString)
                .collect(Collectors.joining("\n"));
    }
}
