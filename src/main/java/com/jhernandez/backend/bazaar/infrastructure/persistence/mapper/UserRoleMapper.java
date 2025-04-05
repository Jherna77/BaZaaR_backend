package com.jhernandez.backend.bazaar.infrastructure.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
// import org.springframework.stereotype.Component;

import com.jhernandez.backend.bazaar.domain.model.UserRole;
import com.jhernandez.backend.bazaar.infrastructure.persistence.entity.UserRoleEntity;

// @Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRoleMapper {

    UserRoleEntity toEntity(UserRole userRole);

    UserRole toDomain(UserRoleEntity userRoleEntity);

    List<UserRole> toDomainList(List<UserRoleEntity> userRoleEntities);

    List<UserRoleEntity> toEntityList(List<UserRole> userRoles);

}
