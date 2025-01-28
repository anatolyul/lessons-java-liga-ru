package ru.hofftech.logisticservice.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class ApiError {
    @Builder.Default
    LocalDateTime timestamp = LocalDateTime.now();
    HttpStatus status;
    List<String> errors;
}
