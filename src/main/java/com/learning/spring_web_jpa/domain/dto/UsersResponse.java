package com.learning.spring_web_jpa.domain.dto;

import java.util.List;

public record UsersResponse(List<UserResponse> results) {
}
