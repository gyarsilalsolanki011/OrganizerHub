package com.ragir.organizer.validation.annotation;

import com.ragir.organizer.validation.validators.PhoneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhoneValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ValidPhone {
    String message() default "Please Inter valid Phone Number";
    Class<?>[] group() default {};
    Class<? extends Payload>[] payload() default {};
}
