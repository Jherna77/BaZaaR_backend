package com.jhernandez.backend.bazaar.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.models.User;
import com.jhernandez.backend.bazaar.domain.models.UserRole;

public interface UserRepositoryPort {

    List<UserRole> getUserRoles();

    Optional<User> createUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(final String email);

    // Optional<User> updateUser(User user);
    Optional<User> updateUser(Long id, User user);

    boolean deleteUserById(Long id);

    // boolean deleteUsers();

}
