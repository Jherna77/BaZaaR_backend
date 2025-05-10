package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Item;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ItemEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemEntityMapper {

    @Mapping(target ="id", ignore = true)
    ItemEntity toEntity(Item item);

    Item toDomain(ItemEntity itemEntity);

    List<ItemEntity> toEntityList(List<Item> itemList);

    List<Item> toDomainList(List<ItemEntity> itemEntityList);

}
