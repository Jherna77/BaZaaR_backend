package com.jhernandez.backend.bazaar.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ExistsByEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExistsByEmail {

    String message() default "{validation.email.exists.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
