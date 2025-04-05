package com.jhernandez.backend.bazaar.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.infrastructure.persistence.entity.CategoryEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    @Mapping(target = "products", ignore = true)
    CategoryEntity toEntity(Category category);

    @Mapping(target = "products", ignore = true)
    Category toDomain(CategoryEntity categoryEntity);

}
