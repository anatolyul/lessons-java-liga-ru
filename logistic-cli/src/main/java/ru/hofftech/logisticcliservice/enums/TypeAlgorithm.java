package ru.hofftech.logisticcliservice.enums;

import lombok.Getter;

import java.util.regex.Pattern;

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

    /**
     * Преобразует строковое представление типа алгоритма в перечисление TypeAlgorithm.
     *
     * @param typeAlgorithm строковое представление типа алгоритма
     * @return перечисление TypeAlgorithm, соответствующее строковому представлению
     */
    public static TypeAlgorithm convertStringToEnum(String typeAlgorithm) {
        for (TypeAlgorithm algorithm : TypeAlgorithm.values()) {
            Pattern pattern = Pattern.compile(algorithm.getCode());
            if (pattern.matcher(typeAlgorithm).matches()) {
                return algorithm;
            }
        }
        return null;
    }
}
