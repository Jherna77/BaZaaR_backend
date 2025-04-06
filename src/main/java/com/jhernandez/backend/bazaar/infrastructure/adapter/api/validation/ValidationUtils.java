package com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

/*
 * FieldValidation is a utility class that provides a method to validate fields in a BindingResult object.
 * It collects the field errors and returns them as a ResponseEntity with a bad request status.
 */
public class ValidationUtils {

    public static ResponseEntity<?> fieldValidation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
