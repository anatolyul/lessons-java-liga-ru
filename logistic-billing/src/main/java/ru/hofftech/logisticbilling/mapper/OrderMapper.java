package ru.hofftech.logisticbilling.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.hofftech.logisticbilling.dto.OrderDto;
import ru.hofftech.logisticbilling.entity.OrderEntity;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderDto toDto(OrderEntity order);

    List<OrderDto> toDtoList(List<OrderEntity> orders);

    @Mapping(target = "outboxEvent", ignore = true)
    OrderEntity toEntity(OrderDto orderDto);
}
