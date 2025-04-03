package com.jhernandez.backend.bazaar.application.usecases;

import org.springframework.stereotype.Service;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.ports.in.DeleteUserUseCase;
import com.jhernandez.backend.bazaar.domain.ports.out.UserRepositoryPort;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public void deleteUser(Long id) throws UserException {
        userRepositoryPort.deleteUserById(id);
    }

}
