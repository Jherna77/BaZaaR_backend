package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.UserRole;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.UserRoleDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRoleDtoMapper {

    UserRoleDto toDto(UserRole userRole);

}
