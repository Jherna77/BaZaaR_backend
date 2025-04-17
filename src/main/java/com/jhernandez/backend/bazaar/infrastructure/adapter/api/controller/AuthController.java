package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.AUTH;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(AUTH)
@CrossOrigin(originPatterns = "*")
@Slf4j
public class AuthController {

    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        log.info("Token is valid");
        // Token authenticated by the filter
        return ResponseEntity.ok().build();
    }
}
