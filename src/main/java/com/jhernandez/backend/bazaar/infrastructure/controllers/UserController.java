package com.jhernandez.backend.bazaar.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.services.UserService;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.models.User;

// import static com.jhernandez.backend.bazaar.configuration.ValidationConfig.fieldValidation;

// import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        log.info("Registering user with email {}", user.getEmail());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
        } catch (UserException e) {
            log.error("Error registering user with email {}", user.getEmail());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        log.info("Getting all users");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        log.info("Getting user with ID {}", id);
        try {
            return userService.getUserById(id).map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (UserException e) {
            log.error("Error getting user with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id) {
        log.info("Updating user with id {}", id);
        try {
            return userService.updateUser(id, user).map(ResponseEntity::ok)
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
            userService.deleteUser(id);
            // return ResponseEntity.ok("User deleted successfully");
            return ResponseEntity.noContent().build();
        } catch (UserException e) {
            log.error("Error deleting user with ID {}", id);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // @PutMapping("/disable/{id}")
    // public ResponseEntity<UserDto> disable(@PathVariable Long id) {
    //     log.info("Disabling user by ID {}", id);
    //     return userService.disable(id).map(ResponseEntity::ok)
    //         .orElse(ResponseEntity.notFound().build());
    // }
}
