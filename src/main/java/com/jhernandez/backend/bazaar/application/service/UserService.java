package com.jhernandez.backend.bazaar.application.service;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.EMAIL_PATTERN;
import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.PASSWORD_PATTERN;
import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.ZIP_CODE_PATTERN;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.jhernandez.backend.bazaar.application.port.ImageServicePort;
import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.UserRoleRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.UserServicePort;
import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.domain.model.User;
import com.jhernandez.backend.bazaar.domain.model.UserRole;

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
    private final UserRoleRepositoryPort userRoleRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder,
            ImageServicePort imageServicePort, UserRoleRepositoryPort userRoleRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
        this.imageServicePort = imageServicePort;
        this.userRoleRepositoryPort = userRoleRepositoryPort;
    }

    @Override
    public void createUser(User user) throws UserException {
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        validateUserRole(user.getRole());
        validateUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
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
        validateUserRole(user.getRole());
        validateUser(user);
        User existingUser = findUserById(user.getId());
        existingUser.setRole(user.getRole());
        // existingUser.setEmail(user.getEmail());
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

    private void validateEmail(String email) throws UserException {
        if (email == null || email.isEmpty())
            throw new UserException("User email is required");
        if (!email.matches(EMAIL_PATTERN))
            throw new UserException("User email must be a valid email address");
        if (existsByEmail(email))
            throw new UserException("User with this email already exists");
    }

    private void validatePassword(String password) throws UserException {
        if (password == null || password.isEmpty())
            throw new UserException("User password is required");
        if (!password.matches(PASSWORD_PATTERN))
            throw new UserException("User password must have at least 8 characters, one uppercase letter,one lowercase letter, one digit, one special character");
    }

    private void validateUserRole(UserRole userRole) throws UserException {
        if (userRole == null)
            throw new UserException("User role name is required");
        if (!userRole.getName().equals(userRoleRepositoryPort.findUserRoleById(userRole.getId()).getName()))
            throw new UserException("User role is not valid");
    }

    private void validateUser(User user) throws UserException {
        if (user.getName() == null || user.getName().isEmpty())
            throw new UserException("User name is required");
        if (user.getSurnames() == null || user.getSurnames().isEmpty())
            throw new UserException("User surnames is required");
        if (user.getAddress() == null || user.getAddress().isEmpty())
            throw new UserException("User address is required");
        if (user.getCity() == null || user.getCity().isEmpty())
            throw new UserException("User city is required");
        if (user.getProvince() == null || user.getProvince().isEmpty())
            throw new UserException("User province is required");
        if (user.getZipCode() == null || user.getZipCode().isEmpty())
            throw new UserException("User zipcode is required");
        if (!user.getZipCode().matches(ZIP_CODE_PATTERN))
            throw new UserException("User zipcode must be a valid zipcode");
    }


}
