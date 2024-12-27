package ru.hofftech.console.packages.service.factory;

import ru.hofftech.console.packages.model.enums.ConsoleCommand;
import ru.hofftech.console.packages.service.LoaderBoxesInTrucksService;
import ru.hofftech.console.packages.service.impl.LoaderBoxesInTrucksFirstAlgService;
import ru.hofftech.console.packages.service.impl.LoaderBoxesInTrucksSecondAlgService;
import ru.hofftech.console.packages.service.impl.LoaderBoxesInTrucksThirdAlgService;

public class LoaderBoxesInTrucksServiceFactory {
    public LoaderBoxesInTrucksService createLoaderBoxesInTrucksService(ConsoleCommand command) {
        return switch (command) {
            case FIRST_ALGORITHM -> new LoaderBoxesInTrucksFirstAlgService();
            case SECOND_ALGORITHM -> new LoaderBoxesInTrucksSecondAlgService();
            case THIRD_ALGORITHM -> new LoaderBoxesInTrucksThirdAlgService();
            default -> throw new IllegalStateException("Unexpected value: " + command);
        };
    }
}
