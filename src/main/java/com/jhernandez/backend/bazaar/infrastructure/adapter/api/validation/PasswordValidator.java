package com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.PUT_REQUEST;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Validates that a password has:
 *      -At least 8 characters
 *      -At least one uppercase letter
 *      -At least one lowercase letter
 *      -At least one digit
 *      -At least one special character (@#$%^&+=)
 * 
 * It is used in the validation of passwords in forms.
 */
@Slf4j
@RequiredArgsConstructor
public class PasswordValidator implements ConstraintValidator<PasswordStrength, String> {

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private final HttpServletRequest request;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        log.info("Validating password...");
        // return password.matches(PASSWORD_PATTERN);

        return request.getMethod().equals(PUT_REQUEST) ? isValidForUpdate(password) : password.matches(PASSWORD_PATTERN);
    }

    private boolean isValidForUpdate(String password) {
        return password == null;// || password.isEmpty() || password.matches(PASSWORD_PATTERN);
    }
}
