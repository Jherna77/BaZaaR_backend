package com.jhernandez.backend.bazaar.infrastructure.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
// import org.springframework.stereotype.Component;

import com.jhernandez.backend.bazaar.domain.models.UserRole;
import com.jhernandez.backend.bazaar.infrastructure.entities.UserRoleEntity;

// @Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRoleMapper {

    UserRoleEntity toEntity(UserRole userRole);

    UserRole toDomain(UserRoleEntity userRoleEntity);

    List<UserRole> toDomainList(List<UserRoleEntity> userRoleEntities);

    List<UserRoleEntity> toEntityList(List<UserRole> userRoles);

}
