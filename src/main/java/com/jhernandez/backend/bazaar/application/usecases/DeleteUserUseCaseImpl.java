package com.jhernandez.backend.bazaar.application.usecases;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.ports.in.DeleteUserUseCase;
import com.jhernandez.backend.bazaar.domain.ports.out.UserRepositoryPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public boolean deleteUser(Long id) throws UserException {
        return userRepositoryPort.deleteUserById(id);
    }

}
