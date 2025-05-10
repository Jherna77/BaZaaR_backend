package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.CartItem;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.CartItemEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartItemEntityMapper {

    CartItemEntity toEntity(CartItem cartItem);

    CartItem toDomain(CartItemEntity cartItemEntity);

    List<CartItemEntity> toEntityList(List<CartItem> cartItemList);

    List<CartItem> toDomainList(List<CartItemEntity> cartItemEntityList);

}
