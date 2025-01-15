package ru.hofftech.console.packages.service.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ru.hofftech.console.packages.model.Box;
import ru.hofftech.console.packages.model.enums.ConsoleCommand;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.service.impl.ParserBoxesServiceCsv;
import ru.hofftech.console.packages.service.impl.ParserBoxesServiceJson;
import ru.hofftech.console.packages.service.impl.ParserBoxesServiceTxt;

import java.util.List;

/**
 * Фабрика для создания сервисов парсинга информации о коробках.
 */
@RequiredArgsConstructor
public class ParserBoxesServiceFactory {

    /**
     * Создает экземпляр сервиса парсинга информации о коробках на основе команды.
     *
     * @param boxRepository репозиторий коробок
     * @param command       команда, определяющая тип сервиса парсинга
     * @return экземпляр сервиса парсинга информации о коробках
     * @throws IllegalStateException если команда не поддерживается
     */
    public List<Box> create(BoxRepository boxRepository,
                            ConsoleCommand command,
                            String commandString) {
        return switch (command) {
            case IMPORT_FILE_JSON -> new ParserBoxesServiceJson(boxRepository, new ObjectMapper()).parse(commandString);
            case IMPORT_FILE_TXT -> new ParserBoxesServiceTxt(boxRepository).parse(commandString);
            case LOAD -> new ParserBoxesServiceCsv(boxRepository).parse(commandString);
            default -> throw new IllegalStateException("Unexpected value: " + command);
        };
    }
}
