package com.example.timelineapi.infrastructure.exception;

public class RestTemplateException extends RuntimeException{
    public RestTemplateException(final String messageKey) {
        super(messageKey);
    }
}