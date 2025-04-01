package com.jhernandez.backend.bazaar.mappers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.jhernandez.backend.bazaar.dto.ProductDto;
import com.jhernandez.backend.bazaar.entities.CategoryEntity;
import com.jhernandez.backend.bazaar.entities.ProductEntity;

// @Mapper(componentModel = "spring")
@Component
public class ProductMapper {

    public ProductDto toDto(ProductEntity product) {
        return new ProductDto(
            product.isEnabled(),
            product.getName(),
            product.getDescription(),
            product.getPrice().toString(),
            product.getDiscountPrice().toString(),
            product.getDiscountRate().toString(),
            product.getCategories().stream()
                .map(CategoryEntity::getName)
                .collect(Collectors.toList()));
    }

}
