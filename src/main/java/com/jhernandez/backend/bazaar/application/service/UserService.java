package com.jhernandez.backend.bazaar.application.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.jhernandez.backend.bazaar.application.port.ImageServicePort;
import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.UserServicePort;
import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.domain.model.User;

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
public class UserService implements UserServicePort {

    private static final Long MASTER_ADMIN_ID = 1L;

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final ImageServicePort imageServicePort;

    public UserService(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder, ImageServicePort imageServicePort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
        this.imageServicePort = imageServicePort;
    }

    @Override
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepositoryPort.saveUser(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepositoryPort.findAllUsers();
    }

    @Override
    public User findUserById(Long id) throws UserException {
        return userRepositoryPort.findUserById(id)
                .orElseThrow(() -> new UserException("User not found"));
    }

    @Override
    public User findUserByEmail(String email) throws UserException {
        return userRepositoryPort.findUserByEmail(email)
                .orElseThrow(() -> new UserException("User not found"));
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepositoryPort.existsByEmail(email);
    }

    @Override
    public void updateUser(User user) throws UserException {
        if (user.getId() == null) throw new UserException("User ID must not be null");
        if (user.getId() == MASTER_ADMIN_ID) throw new UserException("Cannot update the master admin user");
        User existingUser = findUserById(user.getId());
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
        userRepositoryPort.saveUser(existingUser);
    }

    @Override
    public void enableUserById(Long id) throws UserException {
        if (id == null)
            throw new UserException("User ID must not be null");
        User existingUser = findUserById(id);
        if (existingUser.getEnabled()) throw new UserException("User is already enabled");
        existingUser.setEnabled(true);
        userRepositoryPort.saveUser(existingUser);
    }

    @Override
    public void disableUserById(Long id) throws UserException {
        if (id == null)
            throw new UserException("User ID must not be null");
        if (id == MASTER_ADMIN_ID)
            throw new UserException("Cannot disable the master admin user");
        User existingUser = findUserById(id);
        if (!existingUser.getEnabled()) throw new UserException("User is already disabled");
        existingUser.setEnabled(false);
        userRepositoryPort.saveUser(existingUser);
    }

    @Override
    public void deleteUserById(Long id) throws UserException, ImageFileException {
        if (id == null)
            throw new UserException("User ID must not be null");
        if (id == MASTER_ADMIN_ID)
            throw new UserException("Cannot delete the master admin user");
        for (Product product : findUserById(id).getShop()) {
            imageServicePort.deleteImageListByUrl(product.getImagesUrl());
        }
        userRepositoryPort.deleteUserById(id);
    }

}
