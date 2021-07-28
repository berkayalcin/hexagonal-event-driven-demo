package com.example.timelineapi.domain.util;

import java.util.UUID;

public class UUIDUtils {
    private static boolean isFrozen;

    private static UUID uuidSet;

    private UUIDUtils() {
    }

    public static synchronized void freeze() {
        isFrozen = true;
    }

    public static synchronized void freeze(UUID uuid) {
        freeze();
        uuidSet = uuid;
    }

    public static synchronized void unfreeze() {
        isFrozen = false;
        uuidSet = null;
    }

    public static UUID random() {
        UUID uuid = UUID.randomUUID();
        if (isFrozen) {
            if (uuidSet == null) {
                uuidSet = uuid;
            }
            return uuidSet;
        }
        return uuid;
    }
}