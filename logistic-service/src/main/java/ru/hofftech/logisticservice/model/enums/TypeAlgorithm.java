package ru.hofftech.logisticservice.model.enums;

import lombok.Getter;

/**
 * Перечисление типов алгоритмов загрузки коробок в грузовики.
 */
@Getter
public enum TypeAlgorithm {
    /**
     * Алгоритм "одна посылка - одна машина".
     */
    ONE_TO_ONE("one2one"),

    /**
     * Алгоритм максимальной загрузки нескольких посылок по машинам.
     */
    MAXIMUM_LOAD("max"),

    /**
     * Алгоритм равномерной погрузки по машинам.
     */
    UNIFORM_LOAD("uniform");

    private final String code;

    /**
     * Конструктор для типа алгоритма.
     *
     * @param code строковое представление типа алгоритма
     */
    TypeAlgorithm(String code) {
        this.code = code;
    }
}
