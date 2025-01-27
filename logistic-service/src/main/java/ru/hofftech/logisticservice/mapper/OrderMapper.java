package ru.hofftech.logisticservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.hofftech.logisticservice.dto.OrderDto;
import ru.hofftech.logisticservice.entity.OrderEntity;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    @Mapping(target = "amount", ignore = true)
    OrderDto toDto(OrderEntity order);
    List<OrderDto> toDtoList(List<OrderEntity> orders);
    OrderEntity toEntity(OrderDto orderDto);
}
