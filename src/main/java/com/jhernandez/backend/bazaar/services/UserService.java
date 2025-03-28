package com.jhernandez.backend.bazaar.services;

import java.util.List;

import com.jhernandez.backend.bazaar.entities.UserEntity;

public interface UserService {

    List<UserEntity> findAll();

    UserEntity save(UserEntity user);

}
