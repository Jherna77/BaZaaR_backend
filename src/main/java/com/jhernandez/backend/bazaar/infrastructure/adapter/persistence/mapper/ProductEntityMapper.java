package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ProductEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductEntityMapper {

    @Mapping(target = "user.cart", ignore = true)
    ProductEntity toEntity(Product product);
    
    @Mapping(target = "user.cart", ignore = true)
    Product toDomain(ProductEntity productEntity);

}
