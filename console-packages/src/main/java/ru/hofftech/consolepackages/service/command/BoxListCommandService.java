package ru.hofftech.consolepackages.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.consolepackages.model.Command;
import ru.hofftech.consolepackages.repository.BoxRepository;
import ru.hofftech.consolepackages.service.CommandExecutor;
import ru.hofftech.consolepackages.service.FormatterService;

/**
 * Сервис для выполнения команды отображения списка всех коробок.
 */
@Service
@RequiredArgsConstructor
public class BoxListCommandService implements CommandExecutor {
    private final BoxRepository boxRepository;
    private final FormatterService formatterService;

    /**
     * Выполняет команду отображения списка всех коробок.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(Command command) {
        return formatterService.formatBoxesToString(boxRepository.findAll());
    }
}
