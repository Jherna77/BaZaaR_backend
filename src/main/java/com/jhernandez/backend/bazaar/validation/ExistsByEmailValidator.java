package com.jhernandez.backend.bazaar.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jhernandez.backend.bazaar.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// Se comprueba si el email ya existe en la base de datos
// Se utiliza en el registro de usuarios para validar el email
@Component
public class ExistsByEmailValidator implements ConstraintValidator<ExistsByEmail, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userService.existsByEmail(email); 
    }

}
