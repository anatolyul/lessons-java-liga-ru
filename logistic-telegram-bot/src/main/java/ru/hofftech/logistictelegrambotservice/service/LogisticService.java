package ru.hofftech.logistictelegrambotservice.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import ru.hofftech.logistictelegrambotservice.dto.RequestCommandDto;
import ru.hofftech.logistictelegrambotservice.dto.ResponseCommandDto;

public interface LogisticService {

    @PostExchange("/api/v1/command")
    ResponseCommandDto executeCommand(@RequestBody RequestCommandDto requestCommandDto);
}
