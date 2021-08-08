package com.example.tweetapi.infrastructure.exception;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(final String messageKey) {
        super(messageKey);
    }
}