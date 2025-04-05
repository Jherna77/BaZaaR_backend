package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.CategoryDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryDtoMapper {

    CategoryDto toDto(Category category);

}
