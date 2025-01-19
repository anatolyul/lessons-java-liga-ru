package ru.hofftech.console.packages.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.console.packages.model.Command;
import ru.hofftech.console.packages.repository.BoxRepository;
import ru.hofftech.console.packages.service.CommandExecutor;

@Service
@RequiredArgsConstructor
public class BoxListCommandService implements CommandExecutor {
    private final BoxRepository boxRepository;

    /**
     * @param command
     * @return
     */
    @Override
    public String execute(Command command) {
        return boxRepository.findAll();
    }
}
