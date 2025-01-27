package ru.hofftech.logisticservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hofftech.logisticservice.dto.ResponseCommandDto;
import ru.hofftech.logisticservice.model.Command;
import ru.hofftech.logisticservice.service.handler.CommandHandler;

@RestController
@RequestMapping("/api/v1/command")
@RequiredArgsConstructor
public class CommandController {
    private final CommandHandler commandHandler;

    @PostMapping
    public ResponseCommandDto executeCommand(@RequestBody Command command) {
        return ResponseCommandDto.builder()
                .resultCommandExecuted(commandHandler.handle(command))
                .build();
    }
}
