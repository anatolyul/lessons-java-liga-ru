package ru.hofftech.console.packages.service.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
@Service
@RequiredArgsConstructor
public class ParserBoxesServiceFactory {

    /**
     * Создает экземпляр сервиса парсинга информации о коробках на основе команды.
     *
     * @param boxRepository репозиторий коробок
     * @param command       команда, определяющая тип сервиса парсинга
     * @param filePath      путь к файлу для парсинга
     * @return экземпляр сервиса парсинга информации о коробках
     * @throws IllegalStateException если команда не поддерживается
     */
    public List<Box> create(BoxRepository boxRepository, ConsoleCommand command, String filePath) {
        return switch (command) {
            case IMPORT_FILE_JSON -> new ParserBoxesServiceJson(boxRepository).parse(filePath);
            case IMPORT_FILE_TXT -> new ParserBoxesServiceTxt(boxRepository).parse(filePath);
            case LOAD -> new ParserBoxesServiceCsv(boxRepository).parse(filePath);
            default -> throw new IllegalStateException("Unexpected value: " + command);
        };
    }
}
