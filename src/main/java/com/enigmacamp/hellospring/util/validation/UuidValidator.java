package com.enigmacamp.hellospring.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class UuidValidator implements ConstraintValidator<Uuid, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            UUID.fromString(s).toString();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
