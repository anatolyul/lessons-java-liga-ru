package ru.hofftech.console.packages.service.factory;

import ru.hofftech.console.packages.model.enums.TypeAlgorithm;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;
import ru.hofftech.console.packages.service.impl.LoaderBoxesInTrucksOneToOneAlgService;
import ru.hofftech.console.packages.service.impl.LoaderBoxesInTrucksMaxAlgService;
import ru.hofftech.console.packages.service.impl.LoaderBoxesInTrucksUniformAlgService;

/**
 * Фабрика для создания сервисов загрузки коробок в грузовики.
 */
public class LoaderBoxesInTrucksServiceFactory {

    /**
     * Создает экземпляр сервиса загрузки коробок в грузовики на основе типа алгоритма.
     *
     * @param typeAlgorithm тип алгоритма загрузки
     * @return экземпляр сервиса загрузки коробок в грузовики
     */
    public LoaderBoxesInTrucksService createLoaderBoxesInTrucksService(TypeAlgorithm typeAlgorithm) {
        return switch (typeAlgorithm) {
            case ONE_TO_ONE -> new LoaderBoxesInTrucksOneToOneAlgService();
            case MAXIMUM_LOAD -> new LoaderBoxesInTrucksMaxAlgService();
            case UNIFORM_LOAD -> new LoaderBoxesInTrucksUniformAlgService();
        };
    }
}
