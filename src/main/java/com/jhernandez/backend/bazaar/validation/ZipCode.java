package com.jhernandez.backend.bazaar.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ZipCodeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ZipCode {
    String message() default "{validation.zipCode.invalid.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
