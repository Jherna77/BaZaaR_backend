package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Item;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ItemDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemDtoMapper {

    ItemDto toDto(Item item);

    Item toDomain(ItemDto itemDto);

    List<ItemDto> toDtoList(List<Item> itemList);

    List<Item> toDomainList(List<ItemDto> itemDtoList);

}
