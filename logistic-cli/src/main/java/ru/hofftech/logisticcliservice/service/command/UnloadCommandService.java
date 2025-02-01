package ru.hofftech.logisticcliservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticcliservice.dto.command.UnloadCommandDto;
import ru.hofftech.logisticcliservice.service.CommandExecutor;
import ru.hofftech.logisticcliservice.service.LogisticService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для выполнения команды разгрузки грузовиков.
 */
@Service
@RequiredArgsConstructor
public class UnloadCommandService implements CommandExecutor<UnloadCommandDto> {

    private static final int BOX_NAME_INDEX = 0;
    private static final int BOX_COUNT_INDEX = 1;
    private static final String COMMA_SEPARATOR = ",";

    private final LogisticService logisticService;

    /**
     * Выполняет команду разгрузки грузовиков.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(UnloadCommandDto command) {
        List<String[]> boxes = logisticService.unloadBoxes(command);

        return command.isWithCount() ?
                boxes.stream()
                        .map(box -> box[BOX_NAME_INDEX] + COMMA_SEPARATOR + box[BOX_COUNT_INDEX])
                        .collect(Collectors.joining("\n")) :
                boxes.stream()
                        .map(box -> box[BOX_NAME_INDEX])
                        .collect(Collectors.joining("\n"));
    }
}
