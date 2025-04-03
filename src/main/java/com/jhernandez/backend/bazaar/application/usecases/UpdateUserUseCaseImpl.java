package com.jhernandez.backend.bazaar.application.usecases;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.models.User;
import com.jhernandez.backend.bazaar.domain.ports.in.UpdateUserUseCase;
import com.jhernandez.backend.bazaar.domain.ports.out.UserRepositoryPort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Optional<User> updateUser(Long id, User user) throws UserException {
        return userRepositoryPort.updateUser(id, user);
    }

}
