package com.ragir.organizer.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizerRequest {
    @NotBlank(message = "Name is required!")
    @Size(max = 50, message = "Size must not more than 50 character!")
    private String name;

    @NotBlank(message = "Email is required!")
    @Email(message = "Please Enter valid Email")
    @UniqueElements(message = "Email is already registered")
    private String email;

    @NotBlank(message = "Phone Number is required")
    @UniqueElements(message = "Phone Number is already registered")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone Number must be between 10 to 15 digits")
    private String phone;

    @NotBlank(message = "BusinessType is required")
    @Size(max = 100, message = "Business Should not exceed 100 words")
    private String businessType;
}
