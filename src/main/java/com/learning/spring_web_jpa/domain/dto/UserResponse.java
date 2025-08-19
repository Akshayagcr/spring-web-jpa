package com.learning.spring_web_jpa.domain.dto;

import com.learning.spring_web_jpa.domain.enums.Gender;

import java.sql.Timestamp;

public record UserResponse(Long id,
                           Long version,
                           String firstName,
                           String lastName,
                           String email,
                           Gender gender,
                           String city,
                           String country,
                           Timestamp createdAt,
                           Timestamp updatedAt) {

}
