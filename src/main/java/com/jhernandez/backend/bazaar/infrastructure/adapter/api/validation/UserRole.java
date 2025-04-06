package com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = UserRoleValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UserRole {
    String message() default "{validation.role.invalid.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
