package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ProductEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CategoryEntityMapper.class})
public interface ProductEntityMapper {

    @Mapping(target = "owner.shop", ignore = true)
    @Mapping(target = "owner.cart", ignore = true)
    // @Mapping(target = "owner.purchaseOrders", ignore = true)
    @Mapping(target = "owner.saleOrders", ignore = true)
    Product toDomain(ProductEntity productEntity);

    @Mapping(target = "owner.shop", ignore = true)
    @Mapping(target = "owner.cart", ignore = true)
    // @Mapping(target = "owner.purchaseOrders", ignore = true)
    @Mapping(target = "owner.saleOrders", ignore = true)
    ProductEntity toEntity(Product product);
}

// @Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
//         uses = {CategoryEntityMapper.class})
// public interface ProductEntityMapper {

//     @Mapping(target = "owner.shop", ignore = true)
//     ProductEntity toEntity(Product product);
    
//     @Mapping(target = "owner.shop", ignore = true)
//     Product toDomain(ProductEntity productEntity);

// }
