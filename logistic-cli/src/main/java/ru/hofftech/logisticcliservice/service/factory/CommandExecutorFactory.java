package ru.hofftech.logisticcliservice.service.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticcliservice.dto.command.BaseCommandDto;
import ru.hofftech.logisticcliservice.service.CommandExecutor;

import java.util.Map;

/**
 * Фабрика для создания экземпляров CommandExecutor на основе переданной консольной команды.
 */
@Service
@RequiredArgsConstructor
public class CommandExecutorFactory {
    private final Map<Class<? extends BaseCommandDto>, CommandExecutor<? extends BaseCommandDto>> commandExecutorMap;

    /**
     * Создает экземпляр CommandExecutor на основе переданной консольной команды.
     *
     * @param command консольная команда
     * @return экземпляр CommandExecutor, соответствующий переданной команде
     */
    @SuppressWarnings("unchecked")
    public <T extends BaseCommandDto> CommandExecutor<T> create(T command) {
        return (CommandExecutor<T>) commandExecutorMap.get(command.getClass());
    }
}
