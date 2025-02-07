package ru.hofftech.logisticservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.dto.ImportParamDto;
import ru.hofftech.logisticservice.dto.LoadParamDto;
import ru.hofftech.logisticservice.dto.OrderDto;
import ru.hofftech.logisticservice.dto.UnloadParamDto;
import ru.hofftech.logisticservice.exception.FileReadException;
import ru.hofftech.logisticservice.exception.FileWriteException;
import ru.hofftech.logisticservice.model.Truck;
import ru.hofftech.logisticservice.model.TruckForm;
import ru.hofftech.logisticservice.model.enums.TypeAlgorithm;
import ru.hofftech.logisticservice.model.enums.TypeLoadData;
import ru.hofftech.logisticservice.model.enums.TypeOrderProcess;
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
    private final OrderService orderService;
    private final ResultOutSaveService resultOutSaveService;

    public List<Truck> load(LoadParamDto loadParamDto) {
        TruckForm trucksForm = TruckForm.fromString(loadParamDto.getTrucks());
        List<BoxDto> boxes = findBoxesToLoad(loadParamDto.getParcelsText(), loadParamDto.getParcelsFile());

        List<Truck> trucks = loadBoxesIntoTrucks(boxes, trucksForm, loadParamDto.getType());
        OrderDto orderDto = orderService.createOrderDto(
                loadParamDto.getClientName(),
                TypeOrderProcess.LOAD,
                loadParamDto.getDate(),
                trucks
        );
        orderService.saveOrder(orderDto);
        resultOutSaveService.saveOutResult(boxes, trucks, loadParamDto.getOutFilename());

        return trucks;
    }

    public List<String[]> unload(UnloadParamDto unloadParamDto) {
        List<Truck> trucks = loadTrucksFromFile(unloadParamDto.getInFilename());
        OrderDto orderDto = orderService.createOrderDto(
                unloadParamDto.getClientName(),
                TypeOrderProcess.UNLOAD,
                unloadParamDto.getDate(),
                trucks
        );
        orderService.saveOrder(orderDto);

        return unloadTrucksToBoxes(trucks, unloadParamDto.getOutFilename(), unloadParamDto.isWithCount());
    }

    public List<BoxDto> importFile(ImportParamDto importParamDto) {
        TypeLoadData commandParser = TypeLoadData.fromExtension(importParamDto.getFilename());
        List<BoxDto> boxes = parserBoxesServiceFactory.create(commandParser, filePathSearchService.search(importParamDto.getFilename()));

        if (!boxes.isEmpty()) {
            boxService.deleteAll();
            boxes.forEach(boxService::create);
        }

        return boxes;
    }

    private List<Truck> loadBoxesIntoTrucks(List<BoxDto> boxes, TruckForm trucksForm, TypeAlgorithm type) {
        return loaderBoxesInTrucksServiceFactory
                .createLoaderBoxesInTrucksService(type)
                .loadBoxesInTrucks(boxes, trucksForm, 0);
    }

    private List<Truck> loadTrucksFromFile(String fileName) {
        try {
            return unloaderTrucksToBoxesService.loadTrucksFromFile(filePathSearchService.search(fileName));
        } catch (FileReadException | FileWriteException e) {
            throw new IllegalArgumentException("Ошибка выполнения команды: " + e.getMessage());
        }
    }

    private List<String[]> unloadTrucksToBoxes(List<Truck> trucks, String outFilename, boolean withCount) {
        return unloaderTrucksToBoxesService.unloadTrucksToBoxes(trucks, outFilename, withCount);
    }

    private List<BoxDto> findBoxesToLoad(String boxesNames, String boxesFile) {
        List<BoxDto> boxes = new ArrayList<>();
        if (boxesNames != null && !boxesNames.isEmpty()) {
            for (String boxName : boxesNames.replace("n", "\n").replace("\\n", "\n").split("\n")) {
                BoxDto box = boxService.findByName(boxName);
                if (box != null) {
                    boxes.add(box);
                }
            }
        } else if (boxesFile != null && !boxesFile.isEmpty()) {
            boxes = parserBoxesServiceFactory.create(TypeLoadData.LOAD, filePathSearchService.search(boxesFile));
        } else {
            boxes = boxService.findAll();
        }
        return boxes;
    }
}


