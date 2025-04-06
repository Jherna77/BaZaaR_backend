package com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/*
 * Valida que un campo no esté vacío o nulo.
 * Se utiliza en la validación de campos obligatorios en formularios.
 */
public class RequiredFieldValidator implements ConstraintValidator<RequiredField, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value);
    }
}
