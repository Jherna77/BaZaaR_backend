package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Review;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ReviewDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.helper.DateMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { DateMapper.class })
public interface ReviewDtoMapper {

    @Mapping(source = "reviewDate", target = "reviewDate", qualifiedByName = "formatDate")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "user.id", target = "userId")
    ReviewDto toDto(Review review);

    @Mapping(target = "product", expression = "java(new Product(reviewDto.getProductId()))")
    @Mapping(target = "user", expression = "java(new User(reviewDto.getUserId()))")
    @Mapping(target = "reviewDate", ignore = true)
    Review toDomain(ReviewDto reviewDto);

}
