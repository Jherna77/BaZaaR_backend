package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Review;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ReviewDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface reviewDtoMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "user.id", target = "userId")
    ReviewDto toDto(Review review);

    @Mapping(target = "product", expression = "java(new Product(reviewDto.getProductId()))")
    @Mapping(target = "user", expression = "java(new User(reviewDto.getUserId()))")
    Review toDomain(ReviewDto reviewDto);

}
