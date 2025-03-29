package com.jhernandez.backend.bazaar.services;

import java.util.List;

import com.jhernandez.backend.bazaar.dto.UserDto;
import com.jhernandez.backend.bazaar.entities.UserEntity;

public interface UserService {

    List<UserDto> findAll();
    // List<UserEntity> findAll();

    UserDto save(UserEntity user);
    // UserEntity save(UserEntity user);

    // UserDto convertToDto(UserEntity user);

}
