package com.jhernandez.backend.bazaar.services;

import java.util.List;

import com.jhernandez.backend.bazaar.dto.UserDto;
import com.jhernandez.backend.bazaar.entities.UserEntity;

public interface UserService {

    List<UserDto> findAll();

    // UserEntity saveEntity(UserEntity user);

    UserDto save(UserEntity user);

}
