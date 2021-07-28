package com.example.timelineapi.application.validator;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumStringValueValidator implements ConstraintValidator<ValidEnumString, String> {

    private Class<? extends Enum> enumClass;
    private boolean required;
    private String[] expectedValues;

    @Override
    public void initialize(ValidEnumString constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
        this.required = constraintAnnotation.required();
        this.expectedValues = constraintAnnotation.expectedValues();
    }

    @Override
    public boolean isValid(String inputValue, ConstraintValidatorContext context) {
        if (!required && StringUtils.isBlank(inputValue)) {
            return true;
        }
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(enums -> ArrayUtils.isEmpty(expectedValues) || ArrayUtils.contains(expectedValues, enums.name()))
                .anyMatch(enums -> enums.name().equalsIgnoreCase(inputValue));
    }
}