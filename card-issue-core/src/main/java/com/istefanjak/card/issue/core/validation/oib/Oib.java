package com.istefanjak.card.issue.core.validation.oib;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** OIB validation annotation. */
@Constraint(validatedBy = OibValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Oib {

  String message() default "Invalid OIB";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
