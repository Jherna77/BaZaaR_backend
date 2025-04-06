package com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation;

import jakarta.validation.ConstraintValidator;
import lombok.extern.slf4j.Slf4j;

/*
 * Validates that a zip code has 5 digits.
 * It is used in the validation of zip codes in forms.
 */
@Slf4j
public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {

    private static final String ZIP_CODE_PATTERN = "^[0-9]{5}$";

    @Override
    public boolean isValid(String zipCode, jakarta.validation.ConstraintValidatorContext context) {
        log.info("Validating zipCode: {}", zipCode);
  
        return zipCode.matches(ZIP_CODE_PATTERN);
    }
}
