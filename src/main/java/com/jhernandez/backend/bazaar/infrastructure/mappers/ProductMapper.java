package com.jhernandez.backend.bazaar.infrastructure.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
// import org.springframework.stereotype.Component;

import com.jhernandez.backend.bazaar.domain.models.Product;
import com.jhernandez.backend.bazaar.infrastructure.entities.ProductEntity;

// @Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductEntity toEntity(Product product);
    
    Product toDomain(ProductEntity productEntity);
    
    List<Product> toDomainList(List<ProductEntity> productEntities);
    
    List<ProductEntity> toEntityList(List<Product> products);

}
