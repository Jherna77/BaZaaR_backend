package com.jhernandez.backend.bazaar.validation;

import jakarta.validation.ConstraintValidator;
import lombok.extern.slf4j.Slf4j;

/*
 * Valida que un código postal tenga 5 dígitos.
 * Se utiliza en la validación de códigos postales en formularios.
 */
@Slf4j
public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {

    private static final String ZIP_CODE_PATTERN = "^[0-9]{5}$";

    @Override
    public boolean isValid(String zipCode, jakarta.validation.ConstraintValidatorContext context) {
        log.info("Validando zipCode: {}", zipCode);
  
        return zipCode.matches(ZIP_CODE_PATTERN);
    }
}
