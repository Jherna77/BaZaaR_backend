package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.User;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.UserRequestDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.UserResponseDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { UserRoleDtoMapper.class })
public interface UserDtoMapper {

    @Mapping(target = "name", expression = "java(NameDisabler.adjust(user.getName(), user.getEnabled()))")
    UserResponseDto toResponseDto(User user);

    @Mapping(target = "shop", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "orders", ignore = true)
    User toDomain(UserRequestDto userRequestDto);

}
