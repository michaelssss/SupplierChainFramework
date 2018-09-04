package com.michaelssss.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = StartWithLowercaseChecker.class)
@Documented
public @interface ActionUrl {
    String message() default "{must start with lowercase}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
