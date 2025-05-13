package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.port.UserServicePort;
import com.jhernandez.backend.bazaar.domain.exception.DomainException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.UserRequestDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.UserResponseDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.UserDtoMapper;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.USERS;
import static com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation.ValidationUtils.fieldValidation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * UserController is a REST controller that handles HTTP requests related to users.
 * It provides endpoints for creating, retrieving, updating, and deleting users.
 * The controller uses the UserServicePort to perform the operations and the UserDtoMapper to convert between domain models and DTOs.
 */
@RestController
@RequestMapping(USERS)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserServicePort userService;
    private final UserDtoMapper userDtoMapper;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDto user, BindingResult result) {
        log.info("Registering user with email {}", user.getEmail());
        try {
            if (result.hasErrors()) return fieldValidation(result);
            log.debug("No errors found in field validation");
            userService.createUser(userDtoMapper.toDomain(user));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserException e) {
            log.error("Error registering user with email {}", user.getEmail());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserResponseDto> findAllUsers() {
        log.info("Finding all users");
        return userService.findAllUsers().stream()
                .map(userDtoMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        log.info("Finding user with ID {}", id);
        try {
            return ResponseEntity.ok(userDtoMapper.toResponseDto(userService.findUserById(id)));
        } catch (UserException e) {
            log.error("Error getting user with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable String email) {
        log.info("Finding user with email {}", email);
        try {
            return ResponseEntity.ok(userDtoMapper.toResponseDto(userService.findUserByEmail(email)));
        } catch (UserException e) {
            log.error("Error getting user with email {}", email);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserRequestDto user, BindingResult result, @PathVariable Long id) {
        log.info("Updating user with id {}", id);
        try {
            if (result.hasErrors()) return fieldValidation(result);
            log.debug("No errors found in field validation");
            userService.updateUser(userDtoMapper.toDomain(user));
            return ResponseEntity.ok().build();
        } catch (UserException e) {
            log.error("Error updating user with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/enable/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> enableUser(@PathVariable Long id) {
        log.info("Enabling user with ID {}", id);
        try {
            userService.enableUserById(id);
            return ResponseEntity.ok().build();
        } catch (UserException e) {
            log.error("Error enabling user with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/disable/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> disableUser(@PathVariable Long id) {
        log.info("Disabling user with ID {}", id);
        try {
            userService.disableUserById(id);
            return ResponseEntity.ok().build();
        } catch (UserException e) {
            log.error("Error disabling user with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with ID {}", id);
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok().build();
        } catch (DomainException e) {
            log.error("Error deleting user with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
