package com.jhernandez.backend.bazaar.validation;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;

// Se comprueba que el código postal tenga 5 dígitos
public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {

    // private static final Pattern ZIP_CODE_PATTERN = Pattern.compile("^[0-9]{5}$");
    private static final String ZIP_CODE_PATTERN = "^[0-9]{5}$";

    @Override
    public boolean isValid(String zipCode, jakarta.validation.ConstraintValidatorContext context) {
        
        return zipCode.matches(ZIP_CODE_PATTERN);
        // return ZIP_CODE_PATTERN.matcher(zipCode).matches();    
    }
}
