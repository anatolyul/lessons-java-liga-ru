package ru.hofftech.logisticservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.dto.ImportParamDto;
import ru.hofftech.logisticservice.dto.LoadParamDto;
import ru.hofftech.logisticservice.dto.ResponseCommandDto;
import ru.hofftech.logisticservice.dto.UnloadParamDto;
import ru.hofftech.logisticservice.service.BoxActionService;
import ru.hofftech.logisticservice.service.BoxService;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для управления коробками.
 */
@RestController
@RequestMapping("/api/v1/box")
@RequiredArgsConstructor
@Tag(name = "Box-Parses", description = "CRUD операции с данными посылок")
public class BoxController {

    private final BoxService boxService;
    private final BoxActionService boxActionService;

    /**
     * Получает список всех коробок.
     *
     * @return список всех коробок
     */
    @Operation(summary = "Получить список всех коробок",
            description = "Возвращает список всех доступных коробок.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список коробок успешно получен",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BoxDto.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content)})
    @GetMapping
    public List<BoxDto> findAll() {
        return boxService.findAll();
    }

    /**
     * Получает коробку по имени.
     *
     * @param name имя коробки
     * @return коробка с указанным именем
     */
    @Operation(summary = "Получить коробку по имени",
            description = "Возвращает коробку с указанным именем.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Коробка успешно найдена",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BoxDto.class))}),
            @ApiResponse(responseCode = "404", description = "Коробка не найдена",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content)})
    @GetMapping("/{name}")
    public ResponseEntity<BoxDto> findByName(@PathVariable String name) {
        Optional<BoxDto> box = Optional.of(boxService.findByName(name));
        return box.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Создает новую коробку.
     *
     * @param box данные новой коробки
     * @return созданная коробка
     */
    @Operation(summary = "Создать новую коробку",
            description = "Создает новую коробку с указанными данными.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Коробка успешно создана",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BoxDto.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<BoxDto> create(@RequestBody BoxDto box) {
        return ResponseEntity.ok(boxService.create(box));
    }

    /**
     * Обновляет существующую коробку.
     *
     * @param name имя коробки
     * @param box  новые данные коробки
     * @return обновленная коробка
     */
    @Operation(summary = "Обновить существующую коробку",
            description = "Обновляет существующую коробку с указанными данными.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Коробка успешно обновлена",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BoxDto.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content)})
    @PutMapping("/{name}")
    public ResponseEntity<BoxDto> update(@PathVariable @NotNull String name,
                                         @RequestBody BoxDto box) {
        return ResponseEntity.ok(boxService.update(name, box));
    }

    /**
     * Удаляет коробку по имени.
     *
     * @param name имя коробки
     * @return результат удаления
     */
    @Operation(summary = "Удалить коробку по имени",
            description = "Удаляет коробку с указанным именем.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Коробка успешно удалена",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content)})
    @DeleteMapping("/{name}")
    public ResponseEntity<Boolean> delete(@PathVariable @NotNull String name) {
        return ResponseEntity.ok(boxService.delete(name));
    }

    /**
     * Загружает коробки в грузовики.
     *
     * @param loadParamDto параметры загрузки
     * @return результат выполнения команды
     */
    @Operation(summary = "Загрузить коробки в грузовики",
            description = "Загружает коробки в грузовики с указанными параметрами.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Коробки успешно загружены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseCommandDto.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content)})
    @PostMapping("/action/load")
    public ResponseEntity<ResponseCommandDto> load(@RequestBody LoadParamDto loadParamDto) {
        return ResponseEntity.ok(boxActionService.load(loadParamDto));
    }

    /**
     * Разгружает грузовики.
     *
     * @param unloadParamDto параметры разгрузки
     * @return результат выполнения команды
     */
    @Operation(summary = "Разгрузить грузовики",
            description = "Разгружает грузовики с указанными параметрами.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Грузовики успешно разгружены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseCommandDto.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content)})
    @PostMapping("/action/unload")
    public ResponseEntity<ResponseCommandDto> unload(@RequestBody UnloadParamDto unloadParamDto) {
        return ResponseEntity.ok(boxActionService.unload(unloadParamDto));
    }

    /**
     * Импортирует коробки из файла.
     *
     * @param importParamDto параметры импорта
     * @return результат выполнения команды
     */
    @Operation(summary = "Импортировать коробки из файла",
            description = "Импортирует коробки из файла с указанными параметрами.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Коробки успешно импортированы",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseCommandDto.class))}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content)})
    @PostMapping("/action/import")
    public ResponseEntity<ResponseCommandDto> importFile(@RequestBody ImportParamDto importParamDto) {
        return ResponseEntity.ok(boxActionService.importFile(importParamDto));
    }
}
