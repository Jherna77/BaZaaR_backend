package com.jhernandez.backend.bazaar.infrastructure.mappers;

// import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
// import org.springframework.stereotype.Component;

import com.jhernandez.backend.bazaar.domain.models.Category;
import com.jhernandez.backend.bazaar.infrastructure.entities.CategoryEntity;

// @Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    CategoryEntity toEntity(Category category);

    Category toDomain(CategoryEntity categoryEntity);

    // List<Category> toDomainList(List<CategoryEntity> categoryEntities);

    // List<CategoryEntity> toEntityList(List<Category> categories);

}
