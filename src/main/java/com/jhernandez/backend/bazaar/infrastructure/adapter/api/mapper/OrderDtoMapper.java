package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Order;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.OrderDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { ItemDtoMapper.class })
public interface OrderDtoMapper {

    @Mapping(target = "userId", ignore = true)
    OrderDto toDto(Order order);

    Order toDomain(OrderDto orderDto);

}
