package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.CategoryDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryDtoMapper {

    CategoryDto toDto(Category category);

    // @Mapping(target = "enabled", ignore = true)
    Category toDomain(CategoryDto categoryDto);

    List<CategoryDto> toDtoList(List<Category> categories);
    
    List<Category> toDomainList(List<CategoryDto> categoryDtos);


}
