package com.michaelssss.utils;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartWithLowercaseChecker implements ConstraintValidator<ActionUrl, String> {
    @Override
    public void initialize(ActionUrl constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) return false;
        int lastSlashIndex = value.lastIndexOf('/');
        char firstLetter = value.charAt(lastSlashIndex + 1);
        return firstLetter >= 'a' && firstLetter <= 'z';
    }
}
