package ru.hofftech.logisticservice.exception;

public class BoxNotFoundException extends RuntimeException {

    public BoxNotFoundException(String name) {
        super("Не найдена коробка-посылка с именем: " + name);
    }
}
