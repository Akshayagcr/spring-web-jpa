package com.learning.spring_web_jpa.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SortProperty {
    FIRST_NAME("firstName"), LAST_NAME("lastname");
    private final String sortPropertyName;
}
