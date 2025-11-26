package com.ragir.organizer.model.dto;

import com.ragir.organizer.validation.annotation.ValidPhone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
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

    @ValidPhone
    @NotBlank(message = "Phone Number is required")
    @UniqueElements(message = "Phone Number is already registered")
    private String phone;

    @NotBlank(message = "BusinessType is required")
    @Size(max = 100, message = "Business Should not exceed 100 words")
    private String businessType;
}
