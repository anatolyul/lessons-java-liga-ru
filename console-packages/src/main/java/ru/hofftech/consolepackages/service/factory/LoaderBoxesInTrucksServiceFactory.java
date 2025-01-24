package ru.hofftech.consolepackages.service.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.consolepackages.model.enums.TypeAlgorithm;
import ru.hofftech.consolepackages.service.LoaderBoxesInTrucksService;

import java.util.Map;

/**
 * Фабрика для создания сервисов загрузки коробок в грузовики.
 */
@Service
@RequiredArgsConstructor
public class LoaderBoxesInTrucksServiceFactory {
    private final Map<TypeAlgorithm, LoaderBoxesInTrucksService> loaderBoxesInTrucksMap;

    /**
     * Создает экземпляр сервиса загрузки коробок в грузовики на основе типа алгоритма.
     *
     * @param typeAlgorithm тип алгоритма загрузки
     * @return экземпляр сервиса загрузки коробок в грузовики
     */
    public LoaderBoxesInTrucksService createLoaderBoxesInTrucksService(TypeAlgorithm typeAlgorithm) {
        return loaderBoxesInTrucksMap.get(typeAlgorithm);
    }
}
