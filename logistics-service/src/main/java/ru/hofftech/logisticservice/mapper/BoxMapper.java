package ru.hofftech.logisticservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.entity.BoxEntity;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BoxMapper {

    BoxDto toDto(BoxEntity box);

    List<BoxDto> toDtoList(List<BoxEntity> boxes);

    BoxEntity toEntity(BoxDto boxDto);

}
