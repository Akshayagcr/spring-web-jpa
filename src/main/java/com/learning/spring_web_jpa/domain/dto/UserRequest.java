package com.learning.spring_web_jpa.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "firstName cannot be blank")
        String firstName,

        @NotBlank(message = "lastName cannot be blank")
        String lastName,

        @NotBlank(message = "email cannot be blank")
        @Email
        String email,

        String gender,

        String city,

        String country
) {}
