package com.jhernandez.backend.bazaar.services;

import java.util.List;
import java.util.Optional;


import com.jhernandez.backend.bazaar.dto.UserDto;
import com.jhernandez.backend.bazaar.entities.UserEntity;


public interface UserService {

    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    UserDto save(UserEntity user);

    Optional<UserDto> update(Long id, UserEntity user);

    Optional<UserDto> disable(Long id);

    boolean existsByEmail(String email);
}
