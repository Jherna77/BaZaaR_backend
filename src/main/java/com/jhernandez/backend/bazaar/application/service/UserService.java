package com.jhernandez.backend.bazaar.application.service;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.UserServicePort;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.User;

import lombok.RequiredArgsConstructor;

/**
 * This class implements the UserServicePort interface, which defines the contract for user-related operations.
 * It can include methods for creating, retrieving, updating, and deleting users.
 * The actual implementation of these methods would depend on the specific requirements of the application.
 * The UserService class is responsible for implementing the business logic related to users and returns the results to the controller.
 * It interacts with the data layer to perform CRUD operations on users and handles any exceptions that may occur.
 */
@RequiredArgsConstructor
public class UserService implements UserServicePort {
    
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Optional<User> createUser(User user) throws UserException {
        return userRepositoryPort.createUser(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepositoryPort.findAllUsers();
    }

    @Override
    public Optional<User> findUserById(Long id) throws UserException {
        return userRepositoryPort.findUserById(id);
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws UserException {
        return userRepositoryPort.findUserByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepositoryPort.existsByEmail(email);
    }

    @Override
    public Optional<User> updateUser(User user) throws UserException {
        if (user.getId() == 1L) {
            throw new UserException("Cannot update the master admin user");
        }
        return userRepositoryPort.updateUser(user);
    }

    @Override
    public Optional<User> enableUserById(Long id) throws UserException {
        return userRepositoryPort.enableUserById(id);
    }

    @Override
    public Optional<User> disableUserById(Long id) throws UserException {
        if (id == 1L) {
            throw new UserException("Cannot disable the master admin user");
        }

        return userRepositoryPort.disableUserById(id);
    }

    @Override
    public void deleteUserById(Long id) throws UserException {
        if (id == 1L) {
            throw new UserException("Cannot delete the master admin user");
        }
        userRepositoryPort.deleteUserById(id);
    }
    
}
