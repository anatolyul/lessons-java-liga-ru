package ru.hofftech.console.packages.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.console.packages.config.BillingConfig;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.Command;
import ru.hofftech.console.packages.model.enums.Argument;
import ru.hofftech.console.packages.repository.OrderRepository;
import ru.hofftech.console.packages.service.CommandExecutor;
import ru.hofftech.console.packages.service.FormatterService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Сервис для выполнения команд биллинга.
 */
@Service
@RequiredArgsConstructor
public class BillingCommandService implements CommandExecutor {
    private final OrderRepository orderRepository;
    private final BillingConfig billingConfig;
    private final FormatterService formatterService;

    /**
     * Выполняет команду биллинга.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(Command command) {
        Map<Argument, String> arguments = command.getArguments();
        String userId = arguments.get(Argument.USER);
        LocalDate periodFrom = formatterService
                .stringToLocalDate(arguments.get(Argument.PERIOD_FROM)).minusDays(1);
        LocalDate periodTo = formatterService
                .stringToLocalDate(arguments.get(Argument.PERIOD_TO)).plusDays(1);

        return orderRepository.getOrders().stream()
                .filter(order -> order.getUserId().equals(userId))
                .filter(order -> order.getDateLoad().isAfter(periodFrom))
                .filter(order -> order.getDateLoad().isBefore(periodTo))
                .flatMap(order -> {
                    List<Box> boxes = order.getBoxes();
                    int sizeBox = boxes.stream().mapToInt(Box::calcSize).sum();
                    return Stream.of(
                            formatterService.orderOperationToBillingString(
                                    order.getDateLoad(),
                                    order.getTrucks().size(),
                                    sizeBox,
                                    billingConfig.getCostLoad(),
                                    "Погрузка",
                                    periodFrom,
                                    periodTo),
                            formatterService.orderOperationToBillingString(
                                    order.getDateUnload(),
                                    order.getTrucks().size(),
                                    sizeBox,
                                    billingConfig.getCostUnload(),
                                    "Разгрузка",
                                    periodFrom,
                                    periodTo)
                    );
                })
                .collect(Collectors.joining("\n"));
    }
}
