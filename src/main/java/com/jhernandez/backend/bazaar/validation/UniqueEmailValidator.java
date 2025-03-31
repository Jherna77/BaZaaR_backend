package com.jhernandez.backend.bazaar.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jhernandez.backend.bazaar.configuration.SpringContextHolder;
import com.jhernandez.backend.bazaar.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

/*
 * Se comprueba si el email ya existe en la base de datos
 * Se utiliza en el registro de usuarios para validar el email
 */
// @Component
@Slf4j
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{

    // @Autowired
    // private UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        log.info("Validando email: {}", email);

        UserService userService = SpringContextHolder.getBean(UserService.class);

        return !userService.existsByEmail(email); 
    }
}