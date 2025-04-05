package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.User;

public interface UpdateUserUseCase {
    
    Optional<User> updateUser(User user) throws UserException;

}
