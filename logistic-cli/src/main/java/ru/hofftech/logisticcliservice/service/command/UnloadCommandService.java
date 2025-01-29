package ru.hofftech.logisticcliservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticcliservice.dto.command.BaseCommandDto;
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
public class UnloadCommandService implements CommandExecutor {

    private final LogisticService logisticService;

    /**
     * Выполняет команду разгрузки грузовиков.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(BaseCommandDto command) {

        UnloadCommandDto unloadCommandDto = (UnloadCommandDto) command;

        List<String[]> boxes = logisticService.unloadBoxes(unloadCommandDto);

        return unloadCommandDto.isWithCount() ?
                boxes.stream()
                        .map(box -> box[0] + "," + box[1])
                        .collect(Collectors.joining("\n")) :
                boxes.stream()
                        .map(box -> box[0])
                        .collect(Collectors.joining("\n"));
    }
}
