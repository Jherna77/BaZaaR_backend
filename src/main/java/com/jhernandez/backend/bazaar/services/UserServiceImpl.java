package com.jhernandez.backend.bazaar.services;

import java.util.ArrayList;
import java.util.List;
// import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.dto.UserDto;
import com.jhernandez.backend.bazaar.entities.RoleEntity;
import com.jhernandez.backend.bazaar.entities.UserEntity;
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

    // @Autowired
    // private MessageSource messageSource;

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    @Override
    public boolean existsByEmail(String email) {

        return userRepository.existsByEmail(email);
    }

    @Transactional
    private UserEntity saveEntity(UserEntity user) {
        log.info("Saving user entity: {}", user.getEmail());
        handleRoles(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Persisting: {}", user.getEmail());
        UserEntity userPersist = userRepository.save(user);
        log.info("User persisted: {}", userPersist.getEmail());
        return userPersist;
        // return userRepository.save(user);
    }

    @Override
    public UserDto save(UserEntity user){
        // if (userRepository.existsByEmail(user.getEmail())) {
        //     throw new IllegalArgumentException(messageSource.getMessage("exists.UserEntity.email", null, Locale.getDefault()));
        // }
        log.info("Saving user: {}", user.getEmail());
        return convertToDto(saveEntity(user));
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

}
