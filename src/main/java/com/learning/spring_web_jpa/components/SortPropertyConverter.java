package com.learning.spring_web_jpa.components;

import com.learning.spring_web_jpa.domain.enums.SortProperty;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SortPropertyConverter implements Converter<String, SortProperty> {
    @Override
    public SortProperty convert(String source) {
        try {
            return SortProperty.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IllegalArgumentException("Invalid sort property value. Allowed: first_name, last_name");
        }
    }
}