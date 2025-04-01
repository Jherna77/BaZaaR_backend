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

        // if (result.hasFieldErrors()) {
        //     return fieldValidation(result);
        // } else {
        //     return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
        // }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UserEntity user, BindingResult result, @PathVariable Long id) {
        log.info("Updating user: {}", user.getEmail());

        return (result.hasFieldErrors())
            ? fieldValidation(result)
            : userService.update(id, user).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

        // if (!user.getEmail().equals(userService.findById(id).get().getEmail()) && result.hasFieldErrors()) {
        //     log.error("Field validation errors: {}", result.getFieldErrors());
        //     return fieldValidation(result);
        // } else {
        //     return userService.update(id, user).map(ResponseEntity::ok)
        //     .orElse(ResponseEntity.notFound().build());
        // }

        
        // if (result.hasFieldErrors()) {
        //     log.error("Field validation errors: {}", result.getFieldErrors());
        //     return fieldValidation(result);
        // } else {
        //     return userService.update(id, user).map(ResponseEntity::ok)
        //     .orElse(ResponseEntity.notFound().build());
        //     return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, user));
        // }

        // return userService.update(id, user).map(ResponseEntity::ok)
        //         .orElse(ResponseEntity.notFound().build());
        // return ResponseEntity.ok().body(UserService.update(user));


    @PutMapping("/disable/{id}")
    public ResponseEntity<UserDto> disable(@PathVariable Long id) {
        log.info("Disabling user by ID {}", id);
        return userService.disable(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
