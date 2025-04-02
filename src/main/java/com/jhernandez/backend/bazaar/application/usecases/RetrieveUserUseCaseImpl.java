package com.jhernandez.backend.bazaar.application.usecases;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.models.User;
import com.jhernandez.backend.bazaar.domain.models.UserRole;
import com.jhernandez.backend.bazaar.domain.ports.in.RetrieveUserUseCase;
import com.jhernandez.backend.bazaar.domain.ports.out.UserRepositoryPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RetrieveUserUseCaseImpl implements RetrieveUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public List<UserRole> getUserRoles() throws UserException {
        return userRepositoryPort.getUserRoles();
    }

    @Override
    public Optional<User> getUserById(Long id) throws UserException {
        return userRepositoryPort.getUserById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) throws UserException {
        return userRepositoryPort.getUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositoryPort.getAllUsers();
    }

}
