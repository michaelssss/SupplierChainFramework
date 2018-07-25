package com.jzqh.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PhoneChecker.class)
@Documented
public @interface PhoneNumber {
    String message() default "{phone string not right}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
