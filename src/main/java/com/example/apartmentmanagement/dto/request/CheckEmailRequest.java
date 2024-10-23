package com.example.apartmentmanagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckEmailRequest {
    @Email(message = "Email cannot be blank!")
    @NotBlank(message = "Email cannot be blank!")
    private String email;

    @NotBlank(message = "Phone number cannot be left blank!")
    @Pattern(regexp = "^(?:\\+84|0)\\d{9}$", message = "Phone numbers must start with +84 or 0 and end with 9 digits!")
    private String phoneNumber;
}
