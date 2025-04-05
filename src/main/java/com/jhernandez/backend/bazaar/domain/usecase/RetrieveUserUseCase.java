package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.User;

public interface RetrieveUserUseCase {
    
    List<User> findAllUsers();

    Optional<User> findUserById(Long id) throws UserException;

    Optional<User> findUserByEmail(String email) throws UserException;

    boolean existsByEmail(String email);

}
