package com.ragir.organizer.validation.validators;

import com.ragir.organizer.validation.annotation.ValidPhone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.isEmpty()){
            return true; //Handled by @NotBlank
        }

        try {
            return isValidPhoneNumber();
        } catch (IllegalArgumentException e){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "Invalid Phone Number "+ s +"Please Inter Valid Number"
            ).addConstraintViolation();
            return false;
        }
    }

    private boolean isValidPhoneNumber() {
        return false;
    }
}
