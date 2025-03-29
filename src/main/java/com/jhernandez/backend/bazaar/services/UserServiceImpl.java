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
import com.jhernandez.backend.bazaar.repositories.RoleRepository;
import com.jhernandez.backend.bazaar.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAll() {
        return repository.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    // @Transactional(readOnly = true)
    // @Override
    // public List<UserEntity> findAll() {
    //     return repository.findAll();
    // }

    // @Transactional
    // @Override
    public UserDto convertToDto(UserEntity user) {
        return new UserDto(
            user.isEnabled(),
            user.getEmail(),
            user.getName(),
            user.getSurnames(),
            user.getAddress(),
            user.getCity(),
            user.getProvince(),
            user.getZipCode(),
            user.getRoles().stream()
                .map(RoleEntity::getName)
                // .collect(Collectors.joining(", ")));
                .collect(Collectors.toList()));
    }

    @Transactional
    @Override
    public UserDto save(UserEntity user) {
        List<RoleEntity> roles = new ArrayList<>();

        Optional<RoleEntity> roleCustomer = roleRepository.findByName("ROLE_CUSTOMER");
        roleCustomer.ifPresent(roles::add);

        if (user.isShop()) {
            Optional<RoleEntity> roleShop = roleRepository.findByName("ROLE_SHOP");
            roleShop.ifPresent(roles::add);
        }

        if (user.isAdmin()) {
            Optional<RoleEntity> roleAdmin = roleRepository.findByName("ROLE_ADMIN");
            roleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        repository.save(user);
        return convertToDto(user);
    }
    // @Transactional
    // @Override
    // public UserEntity save(UserEntity user) {
    //     List<RoleEntity> roles = new ArrayList<>();

    //     Optional<RoleEntity> roleCustomer = roleRepository.findByName("ROLE_CUSTOMER");
    //     roleCustomer.ifPresent(roles::add);

    //     if (user.isShop()) {
    //         Optional<RoleEntity> roleShop = roleRepository.findByName("ROLE_SHOP");
    //         roleShop.ifPresent(roles::add);
    //     }

    //     if (user.isAdmin()) {
    //         Optional<RoleEntity> roleAdmin = roleRepository.findByName("ROLE_ADMIN");
    //         roleAdmin.ifPresent(roles::add);
    //     }

    //     user.setRoles(roles);

    //     user.setPassword(passwordEncoder.encode(user.getPassword()));
        
    //     return repository.save(user);
    // }

}
