package com.learning.spring_web_jpa.domain.dto;

public record ErrorResponse(int status, String code, String message, String type) {}
