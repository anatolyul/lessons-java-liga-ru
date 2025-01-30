package ru.hofftech.logistictelegrambotservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logistictelegrambotservice.dto.CommandDto;
import ru.hofftech.logistictelegrambotservice.dto.OrderDto;
import ru.hofftech.logistictelegrambotservice.enums.Argument;
import ru.hofftech.logistictelegrambotservice.service.CommandExecutor;
import ru.hofftech.logistictelegrambotservice.service.LogisticService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Сервис для выполнения команд биллинга.
 */
@Service
@RequiredArgsConstructor
public class BillingCommandService implements CommandExecutor {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final LogisticService logisticService;

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

        List<OrderDto> orders = logisticService.findOrdersByNameWithPeriod(userId, periodFrom, periodTo);
        return orders.stream()
                .map(OrderDto::toString)
                .collect(Collectors.joining("\n"));
    }

    public LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }
}
