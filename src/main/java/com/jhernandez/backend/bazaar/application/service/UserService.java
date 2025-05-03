package com.jhernandez.backend.bazaar.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.jhernandez.backend.bazaar.application.port.ProductServicePort;
import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.UserServicePort;
import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.User;

import lombok.RequiredArgsConstructor;

/**
 * This class implements the UserServicePort interface, which defines the
 * contract for user-related operations.
 * It can include methods for creating, retrieving, updating, and deleting
 * users.
 * The actual implementation of these methods would depend on the specific
 * requirements of the application.
 * The UserService class is responsible for implementing the business logic
 * related to users and returns the results to the controller.
 * It interacts with the data layer to perform CRUD operations on users and
 * handles any exceptions that may occur.
 */
@RequiredArgsConstructor
public class UserService implements UserServicePort {

    private static final Long MASTER_ADMIN_ID = 1L;

    private final UserRepositoryPort userRepositoryPort;
    private final ProductServicePort productServicePort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> createUser(User user) throws UserException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepositoryPort.saveUser(user);
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
        if (user.getId() == null) throw new UserException("User ID must not be null");
        if (user.getId() == MASTER_ADMIN_ID) throw new UserException("Cannot update the master admin user");
        User existingUser = findUserById(user.getId())
                .orElseThrow(() -> new UserException("User not found"));
        existingUser.setRole(user.getRole());
        existingUser.setEmail(user.getEmail());
        // existingUser.setPassword(existingUser.getPassword()); 
        // existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        existingUser.setName(user.getName());
        existingUser.setSurnames(user.getSurnames());
        existingUser.setAddress(user.getAddress());
        existingUser.setCity(user.getCity());
        existingUser.setProvince(user.getProvince());
        existingUser.setZipCode(user.getZipCode());
        return userRepositoryPort.saveUser(existingUser);
    }

    @Override
    public Optional<User> enableUserById(Long id) throws UserException {
        if (id == null) throw new UserException("User ID must not be null");
        User existingUser = findUserById(id)
                .orElseThrow(() -> new UserException("User not found"));
        if (existingUser.isEnabled()) throw new UserException("User is already enabled");
        productServicePort.enableProductsByUserId(id);
        existingUser.setEnabled(true);
        return userRepositoryPort.saveUser(existingUser);
        }

    @Override
    public Optional<User> disableUserById(Long id) throws UserException {
        if (id == null) throw new UserException("User ID must not be null");
        if (id == MASTER_ADMIN_ID) throw new UserException("Cannot disable the master admin user");
        User existingUser = findUserById(id)
                .orElseThrow(() -> new UserException("User not found"));
        if (!existingUser.isEnabled()) throw new UserException("User is already disabled");
        productServicePort.disableProductsByUserId(id);
        existingUser.setEnabled(false);
        return userRepositoryPort.saveUser(existingUser);
    }

    @Override
    public void deleteUserById(Long id) throws UserException, ImageFileException {
        if (id == null) throw new UserException("User ID must not be null");
        if (id == MASTER_ADMIN_ID) throw new UserException("Cannot delete the master admin user");
        productServicePort.deleteProductsByUserId(id);
        userRepositoryPort.deleteUserById(id);
    }

}
