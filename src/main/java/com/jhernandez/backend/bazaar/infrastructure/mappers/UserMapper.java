package com.jhernandez.backend.bazaar.infrastructure.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import com.jhernandez.backend.bazaar.domain.models.User;
import com.jhernandez.backend.bazaar.infrastructure.entities.UserEntity;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);

    List<User> toDomainList(List<UserEntity> userEntities);

    List<UserEntity> toEntityList(List<User> users);

}
