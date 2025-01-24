package ru.hofftech.consolepackages.service.factory;

import org.springframework.stereotype.Component;
import ru.hofftech.consolepackages.model.TruckForm;

@Component
public class TruckFormFactory {
    public TruckForm createTruckForm(String form) {
        return new TruckForm(form);
    }
}