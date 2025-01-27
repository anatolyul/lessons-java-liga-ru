package ru.hofftech.logisticservice.service.command;

import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.model.Command;
import ru.hofftech.logisticservice.service.CommandExecutor;

/**
 * Сервис для выполнения неизвестных команд.
 */
@Service
public class UnknownCommandService implements CommandExecutor {

    /**
     * Выполняет неизвестную команду.
     *
     * @param command команда для выполнения
     * @return строка, содержащая сообщение о неподдерживаемой команде
     */
    @Override
    public String execute(Command command) {
        return "Приложение не поддерживает данную команду.";
    }
}
