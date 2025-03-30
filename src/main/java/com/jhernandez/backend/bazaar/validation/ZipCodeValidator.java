package com.jhernandez.backend.bazaar.validation;

import jakarta.validation.ConstraintValidator;

/*
 * Valida que un código postal tenga 5 dígitos.
 * Se utiliza en la validación de códigos postales en formularios.
 */
public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {

    private static final String ZIP_CODE_PATTERN = "^[0-9]{5}$";

    @Override
    public boolean isValid(String zipCode, jakarta.validation.ConstraintValidatorContext context) {
        
        return zipCode.matches(ZIP_CODE_PATTERN);
    }
}
