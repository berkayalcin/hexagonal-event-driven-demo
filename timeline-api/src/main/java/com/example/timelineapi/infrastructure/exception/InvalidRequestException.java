package com.example.timelineapi.infrastructure.exception;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(final String messageKey) {
        super(messageKey);
    }
}