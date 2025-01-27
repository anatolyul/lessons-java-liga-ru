package ru.hofftech.logisticservice.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.service.BoxService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/box")
@RequiredArgsConstructor
public class BoxController {
    private final BoxService boxService;

    @GetMapping
    public List<BoxDto> findAll() {
        return boxService.findAll();
    }

    @GetMapping("/{name}")
    public ResponseEntity<BoxDto> findByName(@PathVariable String name) {
        Optional<BoxDto> boxOptional = boxService.findByName(name);
        return boxOptional.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/find")
    public ResponseEntity<BoxDto> findByFilter(@RequestParam(required = false) String name) {
        Optional<BoxDto> boxOptional = boxService.findByName(name);
        return boxOptional.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<BoxDto> create(@RequestBody BoxDto box) {
        return ResponseEntity.ok(boxService.create(box));
    }

    @PutMapping("/{name}")
    public ResponseEntity<BoxDto> update(@PathVariable @NotNull String name,
                                         @RequestBody BoxDto box) {
        return ResponseEntity.ok(boxService.update(name, box));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Boolean> update(@PathVariable @NotNull String name) {
        return ResponseEntity.ok(boxService.delete(name));
    }
}
