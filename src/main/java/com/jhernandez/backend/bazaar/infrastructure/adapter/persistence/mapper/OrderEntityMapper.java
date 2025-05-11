package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Order;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.OrderEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { ItemEntityMapper.class })
public interface OrderEntityMapper {

    OrderEntity toEntity (Order order);

    Order toDomain(OrderEntity orderEntity);

}
