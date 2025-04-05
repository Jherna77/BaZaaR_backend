package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.User;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.UserEntity;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.UserEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlUserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository userRepository;
    private final UserEntityMapper userMapper;

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
    public List<User> findAllUsers() {
        log.info("Finding all users");
         return userRepository.findAll().stream()
            .map(userMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findUserById(Long id) {
        log.info("Finding user with ID {}", id);
        return userRepository.findById(id).map(userMapper::toDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findUserByEmail(String email) {
        log.info("Finding user with email {}", email);

        throw new UnsupportedOperationException("Unimplemented method 'findUserByEmail'");
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByEmail(String email) {
        log.info("Checking existence of email {}", email);
        return userRepository.existsByEmail(email);
    }

    @Transactional
    @Override
    public Optional<User> updateUser(User user) {
        log.info("Updating user {}", user.getEmail());
        return userRepository.findById(user.getId()).map(existingUser -> {
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

}
