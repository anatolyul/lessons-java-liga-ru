package ru.hofftech.logisticservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.hofftech.logisticservice.dto.BoxDto;
import ru.hofftech.logisticservice.entity.BoxEntity;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BoxMapper {

    @Mapping(target = "width", ignore = true)
    @Mapping(target = "height", ignore = true)
    @Mapping(target = "formCoordinates", ignore = true)
    BoxDto toDto(BoxEntity box);

    List<BoxDto> toDtoList(List<BoxEntity> boxes);

    @Mapping(target = "id", ignore = true)
    BoxEntity toEntity(BoxDto boxDto);

}
