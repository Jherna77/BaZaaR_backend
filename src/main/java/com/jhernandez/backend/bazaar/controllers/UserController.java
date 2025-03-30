package com.jhernandez.backend.bazaar.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.dto.UserDto;
import com.jhernandez.backend.bazaar.entities.UserEntity;
import com.jhernandez.backend.bazaar.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> list() {
        return userService.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserEntity user, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));

            // try {
            //     return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
            // } catch (IllegalArgumentException e) {
            //     return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            // }
        }
    }

    // @PutMapping("/update/{id}")
    // public ResponseEntity<UserDto> update(@RequestBody UserEntity user) {
    //     return ResponseEntity.ok().body(service.update(user));
    // }

    // @DeleteMapping("/delete/{id}")
    // public ResponseEntity<Void> delete(@RequestBody UserEntity user) {
    //     return ResponseEntity.ok().body(service.delete(user.getId()););
    // }

    private ResponseEntity<?> validation (BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
