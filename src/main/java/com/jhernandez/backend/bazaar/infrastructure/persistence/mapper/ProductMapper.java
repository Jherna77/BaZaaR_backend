package com.jhernandez.backend.bazaar.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.infrastructure.persistence.entity.ProductEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    @Mapping(target = "categories", ignore = true)
    ProductEntity toEntity(Product product);
    
    @Mapping(target = "categories", ignore = true)
    Product toDomain(ProductEntity productEntity);

}
