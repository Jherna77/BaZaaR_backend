package com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql.MysqlUserRepositoryAdapter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Se comprueba si el email ya existe en la base de datos
 * Se utiliza en el registro de usuarios para validar el email
 */
@Slf4j
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{

    private final MysqlUserRepositoryAdapter userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        log.info("Validating unique email: {}", email);

        if (userService == null) {
            return true;
        }

        return !userService.existsByEmail(email); 
    }
}