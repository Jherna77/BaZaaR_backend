package com.jhernandez.backend.bazaar.validation;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// Se comprueba que la contraseña tenga:
//      -Al menos 8 caracteres
//      -Al menos una letra mayúscula
//      -Al menos una letra minúscula
//      -Al menos un dígito
//      -Al menos un carácter especial (@#$%^&+=)
public class PasswordValidator implements ConstraintValidator<Password, String> {

    // private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        return password.matches(PASSWORD_PATTERN);
        
        // return PASSWORD_PATTERN.matcher(password).matches();
    }

}
