package com.jhernandez.backend.bazaar.application.usecases;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.models.User;
import com.jhernandez.backend.bazaar.domain.ports.in.CreateUserUseCase;
import com.jhernandez.backend.bazaar.domain.ports.out.UserRepositoryPort;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Optional<User> createUser(User user) throws UserException {
        return userRepositoryPort.createUser(user);
    }

}
