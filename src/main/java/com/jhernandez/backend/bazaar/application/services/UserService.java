package com.jhernandez.backend.bazaar.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.models.User;
import com.jhernandez.backend.bazaar.domain.models.UserRole;
import com.jhernandez.backend.bazaar.domain.ports.in.CreateUserUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.DeleteUserUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.RetrieveUserUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.UpdateUserUseCase;
import com.jhernandez.backend.bazaar.domain.ports.out.UserServicePort;

import lombok.AllArgsConstructor;

// This class is responsible for handling user-related operations and acts as a service layer between the controller and the use cases.
// It implements the UserServicePort interface, which defines the methods for user operations.
@Service
@AllArgsConstructor
public class UserService implements UserServicePort{
    
    private final CreateUserUseCase createUserUseCase;
    private final RetrieveUserUseCase retrieveUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    

    @Override
    public Optional<User> createUser(User user) throws UserException {
        return createUserUseCase.createUser(user);
    }

    @Override
    public List<UserRole> getUserRoles() throws UserException {
        return retrieveUserUseCase.getUserRoles();
    }

    @Override
    public Optional<User> getUserById(Long id) throws UserException {
        return retrieveUserUseCase.getUserById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) throws UserException {
        return retrieveUserUseCase.getUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return retrieveUserUseCase.getAllUsers();
    }

    @Override
    public Optional<User> updateUser(Long id, User user) throws UserException {
        return updateUserUseCase.updateUser(id, user);
    }

    @Override
    public void deleteUser(Long id) throws UserException {
        deleteUserUseCase.deleteUser(id);
    }

}
