package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.User;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.UserRequestDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.UserResponseDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.helper.NameDisabler;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { UserRoleDtoMapper.class, NameDisabler.class })
public interface UserDtoMapper {

    // @Mapping(target = "name", expression = "java(NameDisabler.adjust(user.getName(), user.getEnabled()))")
    @Mapping(target = "name", source = ".", qualifiedByName = "adjustUserName")    
    UserResponseDto toResponseDto(User user);

    @Mapping(target = "products", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "favourites", ignore = true)
    User toDomain(UserRequestDto userRequestDto);

}
