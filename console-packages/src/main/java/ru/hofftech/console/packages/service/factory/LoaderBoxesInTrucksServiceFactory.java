package ru.hofftech.console.packages.service.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.console.packages.model.enums.TypeAlgorithm;
import ru.hofftech.console.packages.repository.TruckRepository;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;
import ru.hofftech.console.packages.service.impl.LoaderBoxesInTrucksMaxAlgService;
import ru.hofftech.console.packages.service.impl.LoaderBoxesInTrucksOneToOneAlgService;
import ru.hofftech.console.packages.service.impl.LoaderBoxesInTrucksUniformAlgService;

/**
 * Фабрика для создания сервисов загрузки коробок в грузовики.
 */
@Service
@RequiredArgsConstructor
public class LoaderBoxesInTrucksServiceFactory {
    private final TruckRepository truckRepository;

    /**
     * Создает экземпляр сервиса загрузки коробок в грузовики на основе типа алгоритма.
     *
     * @param typeAlgorithm тип алгоритма загрузки
     * @return экземпляр сервиса загрузки коробок в грузовики
     */
    public LoaderBoxesInTrucksService createLoaderBoxesInTrucksService(TypeAlgorithm typeAlgorithm) {
        return switch (typeAlgorithm) {
            case ONE_TO_ONE -> new LoaderBoxesInTrucksOneToOneAlgService(truckRepository);
            case MAXIMUM_LOAD -> new LoaderBoxesInTrucksMaxAlgService(truckRepository);
            case UNIFORM_LOAD -> new LoaderBoxesInTrucksUniformAlgService(truckRepository);
        };
    }
}
