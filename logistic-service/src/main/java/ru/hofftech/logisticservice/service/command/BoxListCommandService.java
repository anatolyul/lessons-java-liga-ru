package ru.hofftech.logisticservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.model.Command;
import ru.hofftech.logisticservice.service.BoxService;
import ru.hofftech.logisticservice.service.CommandExecutor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для выполнения команды отображения списка всех коробок.
 */
@Service
@RequiredArgsConstructor
public class BoxListCommandService implements CommandExecutor {
    private final BoxService boxService;

    /**
     * Выполняет команду отображения списка всех коробок.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(Command command) {
        List<BoxDto> boxes = boxService.findAll();
        return boxes.stream()
                .map(BoxDto::toString)
                .collect(Collectors.joining("\n"));
    }
}
