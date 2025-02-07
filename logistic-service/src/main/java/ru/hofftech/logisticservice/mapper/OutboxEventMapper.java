package ru.hofftech.logisticservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.hofftech.logisticservice.dto.OutboxEventDto;
import ru.hofftech.logisticservice.entity.OutboxEventEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OutboxEventMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "order", ignore = true)
    OutboxEventEntity toEntity(OutboxEventDto outboxEventDto);

    OutboxEventDto toDto(OutboxEventEntity outboxEventEntity);
}
