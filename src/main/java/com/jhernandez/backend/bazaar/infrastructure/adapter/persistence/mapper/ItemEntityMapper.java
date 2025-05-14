package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Item;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ItemEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ProductEntityMapper.class})
public interface ItemEntityMapper {

    ItemEntity toEntity(Item cartItem);

    Item toDomain(ItemEntity cartItemEntity);

    List<ItemEntity> toEntityList(List<Item> cartItemList);

    List<Item> toDomainList(List<ItemEntity> cartItemEntityList);

}

// @Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
//         uses = {ProductEntityMapper.class})
// public interface ItemEntityMapper {

//     @Mapping(target = "product.owner", ignore = true)
//     Item toDomain(ItemEntity cartItemEntity);

//     @Mapping(target = "product.owner", ignore = true)
//     ItemEntity toEntity(Item cartItem);

//     List<ItemEntity> toEntityList(List<Item> cartItemList);

//     List<Item> toDomainList(List<ItemEntity> cartItemEntityList);
// }
