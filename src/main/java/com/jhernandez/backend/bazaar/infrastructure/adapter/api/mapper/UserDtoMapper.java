package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.User;
// import com.jhernandez.backend.bazaar.domain.model.UserRole;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.UserRequestDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.UserResponseDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserDtoMapper {

    // @Mapping(source = "user.role.name", target = "role")
    @Mapping(target = "name", expression = "java(NameDisabler.adjust(user.getName(), user.isEnabled()))")
    UserResponseDto toResponseDto(User user);

    @Mapping(target = "enabled", ignore = true)
    User toDomain(UserRequestDto userRequestDto);

    // default String mapRoleToString(UserRole role) {
    //     return role != null ? role.getName() : null;
    // }

}
