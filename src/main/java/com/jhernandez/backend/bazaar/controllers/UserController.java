package com.jhernandez.backend.bazaar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jhernandez.backend.bazaar.configuration.ValidationConfig.fieldValidation;
import com.jhernandez.backend.bazaar.dto.UserDto;
import com.jhernandez.backend.bazaar.entities.UserEntity;
import com.jhernandez.backend.bazaar.services.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> list() {
        log.info("Listing all users");
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        log.info("Getting user by ID: {}", id);
        return userService.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserEntity user, BindingResult result) {
        log.info("Registering user: {}", user.getEmail());
        return (result.hasFieldErrors())
            ? fieldValidation(result)
            : ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody UserEntity user, @PathVariable Long id) {
        String email = user.getEmail();
        log.info("Updating user: {}", email);

        if (!email.equals(userService.findById(id).get().getEmail()) && !userService.existsByEmail(email)) {
            log.info("Email is unique, updating email for user ID: {}", id);
            userService.updateEmail(id, email);
        } 
        return userService.update(id, user).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<UserDto> disable(@PathVariable Long id) {
        log.info("Disabling user by ID {}", id);
        return userService.disable(id).map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
