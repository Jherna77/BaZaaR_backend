package com.jhernandez.backend.bazaar.domain.ports.in;

import com.jhernandez.backend.bazaar.domain.exception.UserException;

public interface DeleteUserUseCase {

    void deleteUser(Long id) throws UserException;

}
