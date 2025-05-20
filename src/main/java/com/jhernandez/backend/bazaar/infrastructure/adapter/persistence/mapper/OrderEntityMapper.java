package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Order;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.OrderEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { ItemEntityMapper.class })
public interface OrderEntityMapper {

    @Mapping(target = "customer.shop", ignore = true)
    @Mapping(target = "customer.cart", ignore = true)
    @Mapping(target = "customer.purchaseOrders", ignore = true)
    OrderEntity toEntity (Order order);

    @Mapping(target = "customer.shop", ignore = true)
    @Mapping(target = "customer.cart", ignore = true)
    @Mapping(target = "customer.purchaseOrders", ignore = true)
    Order toDomain(OrderEntity orderEntity);

}

// @Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
//         uses = {ItemEntityMapper.class})
// public interface OrderEntityMapper {

//     @Mapping(target = "items", ignore = false)
//     Order toDomain(OrderEntity orderEntity);

//     @Mapping(target = "items", ignore = false)
//     OrderEntity toEntity(Order order);
// }
