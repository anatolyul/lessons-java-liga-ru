package ru.hofftech.logistictelegrambotservice.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logistictelegrambotservice.dto.CommandDto;
import ru.hofftech.logistictelegrambotservice.dto.LoadParamDto;
import ru.hofftech.logistictelegrambotservice.dto.TruckDto;
import ru.hofftech.logistictelegrambotservice.enums.Argument;
import ru.hofftech.logistictelegrambotservice.enums.TypeAlgorithm;
import ru.hofftech.logistictelegrambotservice.service.CommandExecutor;
import ru.hofftech.logistictelegrambotservice.service.LogisticService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Сервис для выполнения команды загрузки коробок в грузовики.
 */
@Service
@RequiredArgsConstructor
public class LoadCommandService implements CommandExecutor {

    private final LogisticService logisticService;

    /**
     * Выполняет команду загрузки коробок в грузовики.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(CommandDto command) {
        Map<Argument, String> arguments = command.getArguments();
        LoadParamDto loadParamDto =
                LoadParamDto.builder()
                        .type(TypeAlgorithm.convertStringToEnum(arguments.get(Argument.TYPE)))
                        .parcelsFile(arguments.get(Argument.PARCELS_FILE))
                        .parcelsText(arguments.get(Argument.PARCELS_TEXT))
                        .outFilename(arguments.get(Argument.OUT_FILENAME))
                        .trucks(arguments.get(Argument.TRUCKS))
                        .build();
        List<TruckDto> trucks = logisticService.loadBoxes(loadParamDto);

        return """
                
                Результаты распределения груза:
                """ +
                trucks.stream().map(TruckDto::toString).collect(Collectors.joining("\n"));
    }
}
