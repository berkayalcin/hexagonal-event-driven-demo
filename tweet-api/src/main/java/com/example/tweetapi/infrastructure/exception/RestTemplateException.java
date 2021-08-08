package com.example.tweetapi.infrastructure.exception;

public class RestTemplateException extends RuntimeException{
    public RestTemplateException(final String messageKey) {
        super(messageKey);
    }
}