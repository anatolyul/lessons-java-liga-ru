package ru.hofftech.logistictelegrambotservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logistictelegrambotservice.constants.DateFormat;
import ru.hofftech.logistictelegrambotservice.dto.CommandDto;
import ru.hofftech.logistictelegrambotservice.dto.OrderDto;
import ru.hofftech.logistictelegrambotservice.enums.Argument;
import ru.hofftech.logistictelegrambotservice.service.BillingService;
import ru.hofftech.logistictelegrambotservice.service.CommandExecutor;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Сервис для выполнения команд биллинга.
 */
@Service
@RequiredArgsConstructor
public class BillingCommandService implements CommandExecutor {
    private final BillingService billingService;

    /**
     * Выполняет команду биллинга.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(CommandDto command) {
        Map<Argument, String> arguments = command.getArguments();
        String userId = arguments.get(Argument.USER);
        LocalDate periodFrom = stringToLocalDate(arguments.get(Argument.PERIOD_FROM));
        LocalDate periodTo = stringToLocalDate(arguments.get(Argument.PERIOD_TO));

        return billingService.findOrdersByNameWithPeriod(userId, periodFrom, periodTo)
                .stream()
                .map(OrderDto::toString)
                .collect(Collectors.joining("\n"));
    }

    public LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DateFormat.FORMATTER);
    }
}
