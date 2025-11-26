package com.ragir.organizer.validation.validators;

import com.ragir.organizer.validation.annotation.ValidPhone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {
    private static final String PHONE_PATTERN = "^[0-9]{10,15}$";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {

        // If empty → let @NotBlank handle it
        if (s == null || s.isEmpty()) {
            return true;
        }

        try {
            return isValidPhoneNumber(s);
        } catch (IllegalArgumentException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Invalid Phone Number: " + s + ". Please enter a valid number."
            ).addConstraintViolation();
            return false;
        }
    }

    private boolean isValidPhoneNumber(String phone) {
        // Validate using regex
        if (phone.matches(PHONE_PATTERN)) {
            return true;
        }
        throw new IllegalArgumentException("Invalid phone format");
    }
}
