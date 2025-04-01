package com.jhernandez.backend.bazaar.mappers;
// import org.mapstruct.Mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.jhernandez.backend.bazaar.dto.UserDto;
import com.jhernandez.backend.bazaar.entities.RoleEntity;
import com.jhernandez.backend.bazaar.entities.UserEntity;

// @Mapper(componentModel = "spring")
@Component
public class UserMapper {

    public UserDto toDto(UserEntity user) {
        return new UserDto(
            user.isEnabled(),
            user.getEmail(),
            user.getName(),
            user.getSurnames(),
            user.getAddress(),
            user.getCity(),
            user.getProvince(),
            user.getZipCode(),
            user.getRoles().stream()
                .map(RoleEntity::getName)
                // .collect(Collectors.joining(", ")));
                .collect(Collectors.toList()));
    }

}
