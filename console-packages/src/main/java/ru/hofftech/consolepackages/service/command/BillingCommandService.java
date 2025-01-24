package ru.hofftech.consolepackages.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.consolepackages.config.BillingConfig;
import ru.hofftech.consolepackages.model.Box;
import ru.hofftech.consolepackages.model.Command;
import ru.hofftech.consolepackages.model.Order;
import ru.hofftech.consolepackages.model.enums.Argument;
import ru.hofftech.consolepackages.model.enums.TypeOrderProcess;
import ru.hofftech.consolepackages.repository.OrderRepository;
import ru.hofftech.consolepackages.service.CommandExecutor;
import ru.hofftech.consolepackages.service.FormatterService;

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
        final int DAY_FOR_INCLUDING_IN_PERIOD = 1;
        Map<Argument, String> arguments = command.arguments();
        String userId = arguments.get(Argument.USER);

        LocalDate periodFrom = formatterService
                .stringToLocalDate(arguments.get(Argument.PERIOD_FROM))
                .minusDays(DAY_FOR_INCLUDING_IN_PERIOD);

        LocalDate periodTo = formatterService
                .stringToLocalDate(arguments.get(Argument.PERIOD_TO))
                .plusDays(DAY_FOR_INCLUDING_IN_PERIOD);

        return orderRepository.getOrders().stream()
                .filter(order -> order.getUserId().equals(userId))
                .filter(order -> order.getDateLoad().isAfter(periodFrom))
                .filter(order -> order.getDateLoad().isBefore(periodTo))
                .flatMap(order -> processOrder(order, periodFrom, periodTo))
                .collect(Collectors.joining("\n"));
    }

    private Stream<String> processOrder(Order order, LocalDate periodFrom, LocalDate periodTo) {
        List<Box> boxes = order.getBoxes();
        int sizeBox = boxes.stream().mapToInt(Box::calcSize).sum();
        return Stream.of(
                formatterService.formatOrderOperationToBillingString(
                        order.getDateLoad(),
                        order.getTrucks().size(),
                        sizeBox,
                        billingConfig.getCostLoad(),
                        TypeOrderProcess.LOAD.getCode(),
                        periodFrom,
                        periodTo),
                formatterService.formatOrderOperationToBillingString(
                        order.getDateUnload(),
                        order.getTrucks().size(),
                        sizeBox,
                        billingConfig.getCostUnload(),
                        TypeOrderProcess.UNLOAD.getCode(),
                        periodFrom,
                        periodTo)
        );
    }
}
