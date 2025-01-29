package ru.hofftech.logisticcliservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticcliservice.dto.BoxDto;
import ru.hofftech.logisticcliservice.dto.command.BaseCommandDto;
import ru.hofftech.logisticcliservice.service.CommandExecutor;
import ru.hofftech.logisticcliservice.service.LogisticService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для выполнения команды отображения списка всех коробок.
 */
@Service
@RequiredArgsConstructor
public class BoxListCommandService implements CommandExecutor {

    private final LogisticService logisticService;

    /**
     * Выполняет команду отображения списка всех коробок.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(BaseCommandDto command) {
        List<BoxDto> boxes = logisticService.findAllBoxes();
        return boxes.stream()
                .map(BoxDto::toString)
                .collect(Collectors.joining("\n"));
    }
}
