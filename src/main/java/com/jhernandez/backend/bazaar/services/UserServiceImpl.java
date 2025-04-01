package com.jhernandez.backend.bazaar.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.dto.UserDto;
import com.jhernandez.backend.bazaar.entities.RoleEntity;
import com.jhernandez.backend.bazaar.entities.UserEntity;
import com.jhernandez.backend.bazaar.mappers.UserMapper;
import com.jhernandez.backend.bazaar.repositories.RoleRepository;
import com.jhernandez.backend.bazaar.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
            .map(userMapper::toDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(userMapper::toDto);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDto save(UserEntity user){
        log.info("Saving user: {}", user.getEmail());
        return userMapper.toDto(saveEntity(user));
    }

    @Transactional
    private UserEntity saveEntity(UserEntity user) {
        handleRoles(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Persisting: {}", user.getEmail());
        return userRepository.save(user);
    }

    private void handleRoles(UserEntity user) {
        log.info("Handling roles for user: {}", user.getEmail());
        List<RoleEntity> roles = new ArrayList<>();

        roleRepository.findByName("ROLE_CUSTOMER").ifPresent(roles::add);

        if (user.isShop()) {
            roleRepository.findByName("ROLE_SHOP").ifPresent(roles::add);
        }

        if (user.isAdmin()) {
            roleRepository.findByName("ROLE_ADMIN").ifPresent(roles::add);
        }
        
        user.setRoles(roles);
    }

    @Override
    @Transactional
    public Optional<UserDto> update(Long id, UserEntity user) {
        return userRepository.findById(id).map(userDb -> {
            userDb.setEmail(user.getEmail());
            userDb.setPassword(user.getPassword());
            userDb.setName(user.getName());
            userDb.setSurnames(user.getSurnames());
            userDb.setAddress(user.getAddress());
            userDb.setCity(user.getCity());
            userDb.setProvince(user.getProvince());
            userDb.setZipCode(user.getZipCode());
            userDb.setAdmin(user.isAdmin());
            userDb.setShop(user.isShop());
            userDb.setEnabled(user.isEnabled());
            return save(userDb);
        });
    }

    @Override
    @Transactional
    public Optional<UserDto> disable(Long id) {
        return userRepository.findById(id).map(userDb -> {
            userDb.setEnabled(false);
            return save(userDb);
        });
    }
}
