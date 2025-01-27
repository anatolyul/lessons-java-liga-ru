package ru.hofftech.logisticservice.service.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.model.enums.ConsoleCommand;
import ru.hofftech.logisticservice.service.ParserBoxesService;

import java.util.List;
import java.util.Map;

/**
 * Фабрика для создания сервисов парсинга информации о коробках.
 */
@Service
@RequiredArgsConstructor
public class ParserBoxesServiceFactory {
    private final Map<ConsoleCommand, ParserBoxesService> parserBoxesMap;

    /**
     * Создает экземпляр сервиса парсинга информации о коробках на основе команды.
     *
     * @param command       команда, определяющая тип сервиса парсинга
     * @param filePath      путь к файлу для парсинга
     * @return экземпляр сервиса парсинга информации о коробках
     * @throws IllegalStateException если команда не поддерживается
     */
    public List<BoxDto> create(ConsoleCommand command, String filePath) {
        ParserBoxesService parser = parserBoxesMap.get(command);
        if (parser == null) {
            throw new IllegalStateException("Unexpected value: " + command);
        }

        return parser.parse(filePath);
    }
}
