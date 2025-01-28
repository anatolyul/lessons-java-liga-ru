package ru.hofftech.logisticservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.hofftech.logisticservice.dto.ApiError;
import ru.hofftech.logisticservice.exception.BoxNotFoundException;

import java.util.List;


@RestControllerAdvice
public class ResponseControllerAdvice {

    @ExceptionHandler(value = {BoxNotFoundException.class})
    public ResponseEntity<ApiError> handleBoxNotFoundException(BoxNotFoundException exception) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .errors(List.of(exception.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
