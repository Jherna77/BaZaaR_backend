package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.User;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.UserEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CartItemEntityMapper.class})
public interface UserEntityMapper {

    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);

}
