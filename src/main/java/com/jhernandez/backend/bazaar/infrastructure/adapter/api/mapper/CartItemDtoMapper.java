package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.CartItem;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.CartItemDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { UserDtoMapper.class, ProductDtoMapper.class })
public interface CartItemDtoMapper {

    CartItemDto toDto(CartItem cartItem);

    CartItem toDomain(CartItemDto cartItemdto);

    List<CartItemDto> toDtoList(List<CartItem> cartItemList);

    List<CartItem> toDomainList(List<CartItemDto> cartItemDtoList);

}
