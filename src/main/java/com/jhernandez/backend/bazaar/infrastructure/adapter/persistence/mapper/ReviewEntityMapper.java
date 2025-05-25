package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Review;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ReviewEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { OrderEntityMapper.class })
public interface ReviewEntityMapper {

    ReviewEntity toEntity (Review review);

    Review toDomain(ReviewEntity reviewEntity);

}
