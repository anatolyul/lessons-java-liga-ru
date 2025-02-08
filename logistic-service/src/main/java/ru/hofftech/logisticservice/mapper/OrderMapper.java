package ru.hofftech.logisticservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.hofftech.logisticservice.dto.OrderDto;
import ru.hofftech.logisticservice.entity.OrderEntity;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "outboxEvent", ignore = true)
    OrderEntity toEntity(OrderDto orderDto);
}
