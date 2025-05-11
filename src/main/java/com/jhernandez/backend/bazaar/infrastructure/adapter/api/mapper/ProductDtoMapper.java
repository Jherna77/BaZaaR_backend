package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ProductDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { CategoryDtoMapper.class })
public interface ProductDtoMapper {

    @Mapping(target = "name", expression = "java(NameDisabler.adjust(product.getName(), product.isEnabled()))")
    @Mapping(source = "user.id", target = "userId")
    ProductDto toDto(Product product);

    @Mapping(source = "userId", target = "user.id")
    Product toDomain(ProductDto productDto);

}
