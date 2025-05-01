package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

// import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.DISABLED_ITEM;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.User;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.UserEntity;
// import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.UserRoleEntity;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.UserEntityMapper;
// import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.UserRoleEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlUserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    // private final UserRoleEntityMapper userRoleEntityMapper;
    // private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Optional<User> saveUser(User user) {
        log.info("Saving user {}", user.getEmail());
        UserEntity userEntity = userEntityMapper.toEntity(user);
        // userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
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
    public void deleteUserById(Long id) {
        log.info("Deleting user with ID {}", id);
        userRepository.deleteById(id);
    }

}
