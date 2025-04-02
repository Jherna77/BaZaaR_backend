package com.jhernandez.backend.bazaar.domain.ports.in;

import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.models.User;

public interface UpdateUserUseCase {
    
    Optional<User> updateUser(Long id, User user) throws UserException;

}
