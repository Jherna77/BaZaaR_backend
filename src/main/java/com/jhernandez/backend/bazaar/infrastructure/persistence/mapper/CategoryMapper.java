package com.jhernandez.backend.bazaar.infrastructure.persistence.mapper;

// import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
// import org.springframework.stereotype.Component;

import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.infrastructure.persistence.entity.CategoryEntity;

// @Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    CategoryEntity toEntity(Category category);

    Category toDomain(CategoryEntity categoryEntity);

    // List<Category> toDomainList(List<CategoryEntity> categoryEntities);

    // List<CategoryEntity> toEntityList(List<Category> categories);

}
