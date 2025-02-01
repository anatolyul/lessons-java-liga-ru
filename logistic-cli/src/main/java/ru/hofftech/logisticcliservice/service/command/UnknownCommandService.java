package ru.hofftech.logisticcliservice.service.command;

import org.springframework.stereotype.Service;
import ru.hofftech.logisticcliservice.dto.command.BaseCommandDto;
import ru.hofftech.logisticcliservice.service.CommandExecutor;

/**
 * Сервис для выполнения неизвестных команд.
 */
@Service
public class UnknownCommandService implements CommandExecutor<BaseCommandDto> {

    /**
     * Выполняет неизвестную команду.
     *
     * @param command команда для выполнения
     * @return строка, содержащая сообщение о неподдерживаемой команде
     */
    @Override
    public String execute(BaseCommandDto command) {
        return "Приложение не поддерживает данную команду.";
    }
}
