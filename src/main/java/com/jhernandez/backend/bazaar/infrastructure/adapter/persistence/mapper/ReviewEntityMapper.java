package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Review;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ReviewEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { UserEntityMapper.class, ProductEntityMapper.class })
public interface ReviewEntityMapper {

    @Mapping(target = "user.products", ignore = true)
    @Mapping(target = "user.cart", ignore = true)
    ReviewEntity toEntity (Review review);

    @Mapping(target = "user.products", ignore = true)
    @Mapping(target = "user.cart", ignore = true)
    Review toDomain(ReviewEntity reviewEntity);

    // List<Review> toDomain(List<ReviewEntity> reviewEntities);

}
