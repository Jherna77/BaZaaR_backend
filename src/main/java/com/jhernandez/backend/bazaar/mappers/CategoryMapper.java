package com.jhernandez.backend.bazaar.mappers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.jhernandez.backend.bazaar.dto.CategoryDto;
import com.jhernandez.backend.bazaar.entities.CategoryEntity;
import com.jhernandez.backend.bazaar.entities.ProductEntity;

// import org.mapstruct.Mapper;

// @Mapper(componentModel = "spring")
@Component
public class CategoryMapper {

    public CategoryDto toDto(CategoryEntity category) {
        return new CategoryDto(
                category.isEnabled(),
                category.getName(),
                category.getProducts().stream()
                        .map(ProductEntity::getName)
                        .collect(Collectors.toList()));
    }
}
