package com.example.timelineapi.domain.util;

import org.apache.commons.lang3.StringUtils;

public class CouchbaseUtil {

    private static final String KEY_SPLITTER = "::";

    private CouchbaseUtil() {

    }

    public static String generateKeyWithGivenPrefix(final String prefix, final String id) {
        return StringUtils.join(prefix, KEY_SPLITTER, id);
    }
}