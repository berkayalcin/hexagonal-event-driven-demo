package com.example.timelineapi.domain.exception;

import java.util.function.Supplier;

public final class DomainNotFoundException extends BaseException implements Supplier<DomainNotFoundException> {

    private static final long serialVersionUID = -2018563180599381098L;

    public DomainNotFoundException(final String key) {
        super("domain.entity.notFound", key);
    }

    public DomainNotFoundException(String key, String... args) {
        super(key, args);
    }

    @Override
    public DomainNotFoundException get() {
        return new DomainNotFoundException(getKey(), getArgs());
    }
}