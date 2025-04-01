package com.jhernandez.backend.bazaar.services;

import java.util.ArrayList;
import java.util.List;
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
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    private UserEntity saveEntity(UserEntity user) {
        handleRoles(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Persisting: {}", user.getEmail());
        return userRepository.save(user);
    }

    @Override
    public UserDto save(UserEntity user){
        log.info("Saving user: {}", user.getEmail());
        return userMapper.toDto(saveEntity(user));
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
}
