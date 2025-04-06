package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ProductDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { CategoryDtoMapper.class })
public interface ProductDtoMapper {

    ProductDto toDto(Product product);

    Product toDomain(ProductDto productDto);

}
