package com.learning.spring_web_jpa.domain.model;

import com.learning.spring_web_jpa.domain.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserPatchBody(

        @NotBlank(message = "firstName cannot be blank")
        String firstName,

        @NotBlank(message = "lastName cannot be blank")
        String lastName,

        @NotBlank(message = "email cannot be blank")
        @Email
        String email,

        Gender gender,

        String city,

        String country
) {}
