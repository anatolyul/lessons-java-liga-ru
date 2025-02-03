package ru.hofftech.logisticservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.dto.ImportParamDto;
import ru.hofftech.logisticservice.dto.LoadParamDto;
import ru.hofftech.logisticservice.dto.UnloadParamDto;
import ru.hofftech.logisticservice.exception.FileReadException;
import ru.hofftech.logisticservice.exception.FileWriteException;
import ru.hofftech.logisticservice.model.Truck;
import ru.hofftech.logisticservice.model.TruckForm;
import ru.hofftech.logisticservice.model.enums.TypeLoadData;
import ru.hofftech.logisticservice.service.converter.FilePathSearchService;
import ru.hofftech.logisticservice.service.factory.LoaderBoxesInTrucksServiceFactory;
import ru.hofftech.logisticservice.service.factory.ParserBoxesServiceFactory;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoxActionService {

    private final BoxService boxService;
    private final ParserBoxesServiceFactory parserBoxesServiceFactory;
    private final FilePathSearchService filePathSearchService;
    private final LoaderBoxesInTrucksServiceFactory loaderBoxesInTrucksServiceFactory;
    private final UnloaderTrucksToBoxesService unloaderTrucksToBoxesService;

    public List<Truck> load(LoadParamDto loadParamDto) {
        TruckForm trucksForm = TruckForm.fromString(loadParamDto.getTrucks());
        List<BoxDto> boxes = findBoxesToLoad(
                loadParamDto.getParcelsText(),
                loadParamDto.getParcelsFile());

        return loaderBoxesInTrucksServiceFactory
                .createLoaderBoxesInTrucksService(loadParamDto.getType())
                .loadBoxesInTrucks(boxes,
                        trucksForm,
                        0);
    }

    public List<String[]> unload(UnloadParamDto unloadParamDto) {
        try {
            return unloaderTrucksToBoxesService.unloadTrucksToBoxes(
                    filePathSearchService.search(unloadParamDto.getInFilename()),
                    unloadParamDto.getOutFilename(),
                    unloadParamDto.isWithCount());
        } catch (FileReadException | FileWriteException e) {
            throw new IllegalArgumentException("Ошибка выполнения команды: " + e.getMessage());
        }
    }

    public List<BoxDto> importFile(ImportParamDto importParamDto) {
        TypeLoadData commandParser = TypeLoadData.fromExtension(importParamDto.getFilename());

        List<BoxDto> boxes = parserBoxesServiceFactory.create(commandParser,
                        filePathSearchService.search(importParamDto.getFilename()));
        if (!boxes.isEmpty()) {
            boxService.deleteAll();
            boxes.forEach(boxService::create);
        }

        return boxes;
    }

    private List<BoxDto> findBoxesToLoad(String boxesNames, String boxesFile) {
        List<BoxDto> boxes = new ArrayList<>();
        if (boxesNames != null && !boxesNames.isEmpty()) {
            for (String boxName : boxesNames.replace("n", "\n")
                    .replace("\\n", "\n").split("\n")) {
                BoxDto box = boxService.findByName(boxName);
                if (box != null) {
                    boxes.add(box);
                }
            }
        } else if (boxesFile != null && !boxesFile.isEmpty()) {
            boxes = parserBoxesServiceFactory
                    .create(TypeLoadData.LOAD,
                            filePathSearchService.search(boxesFile));
        } else {
            boxes = boxService.findAll();
        }
        return boxes;
    }
}
