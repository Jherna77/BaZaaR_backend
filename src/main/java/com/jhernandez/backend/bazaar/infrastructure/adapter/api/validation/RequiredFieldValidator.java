package com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/*
 * Validates that a field is not empty or null.
 * It is used in the validation of required fields in forms.
 */
public class RequiredFieldValidator implements ConstraintValidator<RequiredField, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value);
    }
}
