package com.example.tweetapi.domain.exception;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public abstract class BaseException extends RuntimeException {

    private final String key;
    private final String[] args;

    protected BaseException(String key) {
        this.key = key;
        this.args = ArrayUtils.EMPTY_STRING_ARRAY;
    }

    protected BaseException(String key, String... args) {
        this.key = key;
        this.args = args;
    }

    public String getKey() {
        return this.key;
    }

    public String[] getArgs() {
        return args;
    }

    @Override
    public String getMessage() {
        return "Business exception occurred " + key + " " + StringUtils.join(args);
    }

}