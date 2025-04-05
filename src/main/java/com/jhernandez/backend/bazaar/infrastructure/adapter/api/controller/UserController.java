package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.port.UserServicePort;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.User;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.UserDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.UserDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * UserController is a REST controller that handles HTTP requests related to users.
 * It provides endpoints for creating, retrieving, updating, and deleting users.
 * The controller uses the UserServicePort to perform the operations and the UserDtoMapper to convert between domain models and DTOs.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserServicePort userService;
    private final UserDtoMapper userDtoMapper;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        log.info("Registering user with email {}", user.getEmail());
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(user).map(userDtoMapper::toDto));
        } catch (UserException e) {
            log.error("Error registering user with email {}", user.getEmail());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<UserDto> findAllUsers() {
        log.info("Finding all users");
        return userService.findAllUsers().stream()
                .map(userDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        log.info("Finding user with ID {}", id);
        try {
            return userService.findUserById(id).map(userDtoMapper::toDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (UserException e) {
            log.error("Error getting user with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id) {
        log.info("Updating user with id {}", id);
        user.setId(id);
        try {
            return userService.updateUser(user).map(userDtoMapper::toDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (UserException e) {
            log.error("Error updating user with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with ID {}", id);
        try {
            userService.deleteUserById(id);
            // return ResponseEntity.ok("User deleted successfully");
            return ResponseEntity.noContent().build();
        } catch (UserException e) {
            log.error("Error deleting user with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
