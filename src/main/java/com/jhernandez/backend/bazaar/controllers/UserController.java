package com.jhernandez.backend.bazaar.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.dto.UserDto;
import com.jhernandez.backend.bazaar.entities.UserEntity;
import com.jhernandez.backend.bazaar.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<UserDto> list() {
        return service.findAll().stream()
            .map(service::convertToDto)
            .collect(Collectors.toList());
    }

    // @GetMapping
    // public List<UserEntity> list() {
    //    return service.findAll();
    // }

    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
        // return ResponseEntity.ok(service.save(user));
    }
}
