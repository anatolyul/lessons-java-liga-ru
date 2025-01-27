package ru.hofftech.logisticservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.entity.BoxEntity;
import ru.hofftech.logisticservice.mapper.BoxMapper;
import ru.hofftech.logisticservice.repository.BoxRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoxService {

    private final BoxRepository boxRepository;
    private final BoxMapper boxMapper;

    public List<BoxDto> findAll() {
        return boxMapper.toDtoList(boxRepository.findAll());
    }

    public Optional<BoxDto> findById(Long id) {
        Optional<BoxEntity> box = boxRepository.findById(id);
        return box.map(boxMapper::toDto);
    }

    public Optional<BoxDto> findByName(String name) {
        Optional<BoxEntity> box = boxRepository.findByName(name);
        return box.map(boxMapper::toDto);
    }

    public BoxDto create(BoxDto boxDto) {
        BoxEntity box = boxMapper.toEntity(boxDto);
        return boxMapper.toDto(boxRepository.create(box));
    }

    public BoxDto update(String name, BoxDto boxDto) {
        BoxEntity box = boxMapper.toEntity(boxDto);
        return boxMapper.toDto(boxRepository.update(name, box));
    }

    public boolean delete(String name) {
        return boxRepository.delete(name);
    }
}
