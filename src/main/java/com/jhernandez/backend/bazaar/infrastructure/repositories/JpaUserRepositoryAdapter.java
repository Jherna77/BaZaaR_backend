package com.jhernandez.backend.bazaar.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.domain.models.User;
import com.jhernandez.backend.bazaar.domain.models.UserRole;
import com.jhernandez.backend.bazaar.domain.ports.out.UserRepositoryPort;
import com.jhernandez.backend.bazaar.infrastructure.entities.UserEntity;
import com.jhernandez.backend.bazaar.infrastructure.mappers.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JpaUserRepositoryAdapter implements UserRepositoryPort {

    @Autowired
    private JpaUserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public List<UserRole> getUserRoles() {
        log.info("Getting all user roles");
        // return userRepository.findAllUserRoles().stream()
        //         .map(userMapper::toDomain)
        //         .toList();

        throw new UnsupportedOperationException("Unimplemented method 'getUserRoles'");
    }

    @Transactional
    @Override
    public Optional<User> createUser(User user) {
        log.info("Creating user {}", user.getEmail());
        UserEntity userEntity = userMapper.toEntity(user);
        return Optional.of(userMapper.toDomain(
                userRepository.save(userEntity)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        log.info("Getting all users");
         return userRepository.findAll().stream()
            .map(userMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUserById(Long id) {
        log.info("Getting user with ID {}", id);
        return userRepository.findById(id).map(userMapper::toDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUserByEmail(String email) {
        log.info("Getting user by email {}", email);

        throw new UnsupportedOperationException("Unimplemented method 'getUserByEmail'");
    }

    @Transactional
    @Override
    public Optional<User> updateUser(Long id, User user) {
        log.info("Updating user with ID {}", id);
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setName(user.getName());
            existingUser.setSurnames(user.getSurnames());
            existingUser.setAddress(user.getAddress());
            existingUser.setCity(user.getCity());
            existingUser.setProvince(user.getProvince());
            existingUser.setZipCode(user.getZipCode());
            existingUser.setEnabled(user.isEnabled());
            return userMapper.toDomain(userRepository.save(existingUser));
        });
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        log.info("Deleting user with ID {}", id);
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByEmail(String email) {
        log.info("Checking existence of email {}", email);
        return userRepository.existsByEmail(email);
    }

    

}
