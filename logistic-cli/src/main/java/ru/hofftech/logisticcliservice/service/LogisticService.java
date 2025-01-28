package ru.hofftech.logisticcliservice.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import ru.hofftech.logisticcliservice.dto.RequestCommandDto;
import ru.hofftech.logisticcliservice.dto.ResponseCommandDto;

public interface LogisticService {

    @PostExchange("/api/v1/command")
    ResponseCommandDto executeCommand(@RequestBody RequestCommandDto requestCommandDto);
}
