package com.example.tweetapi.infrastructure.model;

import lombok.*;
import org.apache.commons.lang3.ArrayUtils;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorDetail {
    private String key;
    private String message;
    private String errorCode;
    private String[] args = ArrayUtils.EMPTY_STRING_ARRAY;
}