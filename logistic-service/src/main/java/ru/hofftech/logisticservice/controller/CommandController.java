package ru.hofftech.logisticservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hofftech.logisticservice.dto.ResponseCommandDto;
import ru.hofftech.logisticservice.model.Command;
import ru.hofftech.logisticservice.service.handler.CommandHandler;

/**
 * Контроллер для выполнения команд.
 */
@RestController
@RequestMapping("/api/v1/command")
@RequiredArgsConstructor
public class CommandController {

    private final CommandHandler commandHandler;

    /**
     * Выполняет команду.
     *
     * @param command команда для выполнения
     * @return результат выполнения команды
     */
    @Operation(summary = "Выполнить команду",
            description = "Выполняет указанную команду и возвращает результат выполнения.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Команда успешно выполнена",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseCommandDto.class))}),
            @ApiResponse(responseCode = "400", description = "Неверные параметры запроса",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content)})
    @PostMapping
    public ResponseCommandDto executeCommand(@RequestBody Command command) {
        return ResponseCommandDto.builder()
                .resultCommandExecuted(commandHandler.handle(command))
                .build();
    }
}
