package com.jhernandez.backend.bazaar.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

/*
 * Valida que una contraseña tenga:
 *      -Al menos 8 caracteres
 *      -Al menos una letra mayúscula
 *      -Al menos una letra minúscula
 *      -Al menos un dígito
 *      -Al menos un carácter especial (@#$%^&+=)
 * 
 * Se utiliza en la validación de contraseñas en formularios.
 */
@Slf4j
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        log.info("Validando password...");
        return password.matches(PASSWORD_PATTERN);
    }
}
