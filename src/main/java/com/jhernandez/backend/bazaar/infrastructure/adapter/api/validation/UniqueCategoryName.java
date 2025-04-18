package com.jhernandez.backend.bazaar.infrastructure.adapter.api.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = UniqueCategoryNameValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCategoryName {

    String message() default "{validation.category.exists.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}



