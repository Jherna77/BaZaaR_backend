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

import lombok.AllArgsConstructor;

// This class is responsible for handling user-related operations and acts as a service layer between the controller and the use cases.
// It implements the UserServicePort interface, which defines the methods for user operations.
@Service
@AllArgsConstructor
public class UserService {
    
    private final CreateUserUseCase createUserUseCase;
    private final RetrieveUserUseCase retrieveUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    
    public Optional<User> createUser(User user) throws UserException {
        return createUserUseCase.createUser(user);
    }

    public List<UserRole> getUserRoles() throws UserException {
        return retrieveUserUseCase.getUserRoles();
    }

    public Optional<User> getUserById(Long id) throws UserException {
        return retrieveUserUseCase.getUserById(id);
    }

    public Optional<User> getUserByEmail(String email) throws UserException {
        return retrieveUserUseCase.getUserByEmail(email);
    }

    public List<User> getAllUsers() {
        return retrieveUserUseCase.getAllUsers();
    }

    public Optional<User> updateUser(Long id, User user) throws UserException {
        return updateUserUseCase.updateUser(id, user);
    }

    public void deleteUser(Long id) throws UserException {
        deleteUserUseCase.deleteUser(id);
    }

}
