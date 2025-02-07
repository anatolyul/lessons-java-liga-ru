package ru.hofftech.logisticbilling.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.hofftech.logisticbilling.dto.InboxEventDto;
import ru.hofftech.logisticbilling.entity.InboxEventEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InboxEventMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "order", ignore = true)
    InboxEventEntity toEntity(InboxEventDto inboxEventDto);

    InboxEventDto toDto(InboxEventEntity outboxEventEntity);
}
