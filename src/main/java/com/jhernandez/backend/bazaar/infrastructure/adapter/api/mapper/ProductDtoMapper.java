package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ProductDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.helper.NameDisabler;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { CategoryDtoMapper.class, UserDtoMapper.class, NameDisabler.class })
public interface ProductDtoMapper {

    // @Mapping(target = "name", expression = "java(NameDisabler.adjust(product.getName(), product.getEnabled()))")
    @Mapping(target = "name", source = ".", qualifiedByName = "adjustProductName")    
    @Mapping(source = "shop.id", target = "shopId")
    ProductDto toDto(Product product);

    @Mapping(target = "shop", expression = "java(new User(productDto.getShopId()))")
    Product toDomain(ProductDto productDto);

    List<ProductDto> toDtoList(List<Product> products);

}
