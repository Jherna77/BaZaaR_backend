package com.jhernandez.backend.bazaar.domain.ports.in;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.models.User;
import com.jhernandez.backend.bazaar.domain.models.UserRole;

public interface RetrieveUserUseCase {

    List<UserRole> getUserRoles() throws UserException;

    Optional<User> getUserById(Long id) throws UserException;

    Optional<User> getUserByEmail(String email) throws UserException;

    List<User> getAllUsers();

}
