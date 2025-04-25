package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

// import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.DISABLED_ITEM;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.User;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.UserEntity;
// import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.UserRoleEntity;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.UserEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.UserRoleEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlUserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final UserRoleEntityMapper userRoleEntityMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Optional<User> createUser(User user) {
        log.info("Creating user {}", user.getEmail());
        UserEntity userEntity = userEntityMapper.toEntity(user);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return Optional.of(userEntityMapper.toDomain(
                userRepository.save(userEntity)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAllUsers() {
        log.info("Finding all users");
         return userRepository.findAll().stream()
            .map(userEntityMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findUserById(Long id) {
        log.info("Finding user with ID {}", id);
        return userRepository.findById(id).map(userEntityMapper::toDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findUserByEmail(String email) {
        log.info("Finding user with email {}", email);
        return userRepository.findByEmail(email)
            .map(userEntityMapper::toDomain);
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
            existingUser.setRole(userRoleEntityMapper.toEntity(user.getRole()));
            existingUser.setEmail(user.getEmail());
            // existingUser.setPassword(existingUser.getPassword()); // Do not update password
            // existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            existingUser.setName(user.getName());
            existingUser.setSurnames(user.getSurnames());
            existingUser.setAddress(user.getAddress());
            existingUser.setCity(user.getCity());
            existingUser.setProvince(user.getProvince());
            existingUser.setZipCode(user.getZipCode());
            return userEntityMapper.toDomain(userRepository.save(existingUser));
        });
    }

    @Override
    public Optional<User> enableUserById(Long id) {
        log.info("Enabling user with ID {}", id);
        return userRepository.findById(id).map(user -> {
            // user.setName(user.getName().replace(DISABLED_ITEM, ""));
            user.setEnabled(true);
            return userEntityMapper.toDomain(userRepository.save(user));
        });
    }

    @Override
    public Optional<User> disableUserById(Long id) {
        log.info("Disabling user with ID {}", id);
        return userRepository.findById(id).map(user -> {
            // user.setName(user.getName() + DISABLED_ITEM);
            user.setEnabled(false);
            return userEntityMapper.toDomain(userRepository.save(user));
        });
    }


    @Transactional
    @Override
    public void deleteUserById(Long id) {
        log.info("Deleting user with ID {}", id);
        userRepository.deleteById(id);
    }

}
