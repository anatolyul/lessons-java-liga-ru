package ru.hofftech.logistictelegrambotservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logistictelegrambotservice.dto.CommandDto;
import ru.hofftech.logistictelegrambotservice.dto.UnloadParamDto;
import ru.hofftech.logistictelegrambotservice.enums.Argument;
import ru.hofftech.logistictelegrambotservice.service.CommandExecutor;
import ru.hofftech.logistictelegrambotservice.service.LogisticService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Сервис для выполнения команды разгрузки грузовиков.
 */
@Service
@RequiredArgsConstructor
public class UnloadCommandService implements CommandExecutor {

    private static final int BOX_NAME_INDEX = 0;
    private static final int BOX_COUNT_INDEX = 1;

    private final LogisticService logisticService;

    /**
     * Выполняет команду разгрузки грузовиков.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(CommandDto command) {
        Map<Argument, String> arguments = command.getArguments();

        boolean withCount = arguments.get(Argument.WITHCOUNT) != null
                && arguments.get(Argument.WITHCOUNT).equals("true");

        UnloadParamDto unloadParamDto = UnloadParamDto.builder()
                .inFilename(arguments.get(Argument.IN_FILENAME))
                .outFilename(arguments.get(Argument.OUT_FILENAME))
                .withCount(withCount)
                .build();

        List<String[]> boxes = logisticService.unloadBoxes(unloadParamDto);

        return withCount ?
                boxes.stream()
                        .map(box -> box[BOX_NAME_INDEX] + "," + box[BOX_COUNT_INDEX])
                        .collect(Collectors.joining("\n")) :
                boxes.stream()
                        .map(box -> box[BOX_NAME_INDEX])
                        .collect(Collectors.joining("\n"));
    }
}
