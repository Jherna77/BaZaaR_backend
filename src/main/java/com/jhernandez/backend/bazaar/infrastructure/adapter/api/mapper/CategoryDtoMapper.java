package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.CategoryDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.helper.NameDisabler;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { NameDisabler.class })
public interface CategoryDtoMapper {

    @Mapping(target = "name", source = ".", qualifiedByName = "adjustCategoryName")
    // @Mapping(target = "name", expression = "java(NameDisabler.adjust(category.getName(), category.getEnabled()))")
    CategoryDto toDto(Category category);

    Category toDomain(CategoryDto categoryDto);

    List<CategoryDto> toDtoList(List<Category> categories);

    List<Category> toDomainList(List<CategoryDto> categoryDtos);

}
