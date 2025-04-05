package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.UserException;

public interface DeleteUserUseCase {

    void deleteUserById(Long id) throws UserException;

}
