package ru.hofftech.logisticservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.dto.BoxForUpdateDto;
import ru.hofftech.logisticservice.entity.BoxEntity;
import ru.hofftech.logisticservice.exception.BoxNotFoundException;
import ru.hofftech.logisticservice.mapper.BoxMapper;
import ru.hofftech.logisticservice.repository.BoxRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoxService {

    private final BoxRepository boxRepository;
    private final BoxMapper boxMapper;

    public List<BoxDto> findAll() {
        return boxMapper.toDtoList(boxRepository.findAll());
    }

    public BoxDto findByName(String name) {
        BoxEntity box = boxRepository.findByName(name)
                .orElseThrow(() -> new BoxNotFoundException(name));
        return boxMapper.toDto(box);
    }

    public BoxDto create(BoxDto boxDto) {
        BoxEntity box = boxMapper.toEntity(boxDto);
        return boxMapper.toDto(boxRepository.save(box));
    }

    public BoxDto update(BoxForUpdateDto boxDto) {
        BoxEntity box = boxRepository.findByName(boxDto.getOldName())
                .orElseThrow(() -> new BoxNotFoundException(boxDto.getOldName()));
        box.setName(boxDto.getName());
        box.setForm(boxDto.getForm());
        box.setSymbol(boxDto.getSymbol());
        return boxMapper.toDto(boxRepository.save(box));
    }

    public boolean delete(String name) {
        BoxEntity box = boxRepository.findByName(name)
                .orElseThrow(() -> new BoxNotFoundException(name));
        boxRepository.delete(box);
        return boxRepository.findByName(name).isEmpty();
    }

    public void deleteAll() {
        boxRepository.deleteAll();
    }
}
