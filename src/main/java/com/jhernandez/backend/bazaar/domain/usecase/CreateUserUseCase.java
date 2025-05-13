package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.User;

public interface CreateUserUseCase {

    void createUser(User user) throws UserException;

}
