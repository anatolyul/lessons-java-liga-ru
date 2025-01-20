package ru.hofftech.console.packages.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.console.packages.model.Command;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.service.CommandExecutor;

/**
 * Сервис для выполнения команды отображения списка всех коробок.
 */
@Service
@RequiredArgsConstructor
public class BoxListCommandService implements CommandExecutor {
    private final BoxRepository boxRepository;

    /**
     * Выполняет команду отображения списка всех коробок.
     *
     * @param command команда для выполнения
     * @return строка, содержащая результат выполнения команды
     */
    @Override
    public String execute(Command command) {
        return boxRepository.findAll();
    }
}
