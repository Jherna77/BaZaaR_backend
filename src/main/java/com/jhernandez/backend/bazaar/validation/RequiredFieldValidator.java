package com.jhernandez.backend.bazaar.validation;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequiredFieldValidator implements ConstraintValidator<RequiredField, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value);
    }
}
