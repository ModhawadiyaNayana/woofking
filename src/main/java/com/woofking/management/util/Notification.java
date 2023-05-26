package com.woofking.management.util;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class Notification {
    @NotBlank(message = "First name is required.")
    @Pattern(regexp = "[a-zA-Z]+", message = "First name must only contain letters.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Pattern(regexp = "[a-zA-Z]+", message = "Last name must only contain letters.")
    private String lastName;

    @Email(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "Invalid email format.")
    private String email;

    @Pattern(regexp = "\\d{10}$", message = "Invalid phone number format.")
    private String phoneNumber;

    @NotBlank(message = "Supervisor is required.")
    private String supervisor;
}
