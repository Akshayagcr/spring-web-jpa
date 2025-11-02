package com.learning.spring_web_jpa.components;

import com.learning.spring_web_jpa.domain.enums.SortDirection;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SortDirectionConverter implements Converter<String, SortDirection> {
    @Override
    public SortDirection convert(String source) {
        try {
            return SortDirection.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IllegalArgumentException("Invalid sort direction value. Allowed: asc, desc");
        }
    }
}
