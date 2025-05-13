package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ProductDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { CategoryDtoMapper.class })
public interface ProductDtoMapper {

    @Mapping(target = "name", expression = "java(NameDisabler.adjust(product.getName(), product.getEnabled()))")
    @Mapping(target = "userId", ignore = true)
    ProductDto toDto(Product product);

    Product toDomain(ProductDto productDto);

}
