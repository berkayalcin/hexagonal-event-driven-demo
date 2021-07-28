package com.example.timelineapi.infrastructure.constant;

public class AuditionConstants {
    public static final String X_CORRELATION_ID = "x-correlationId";
    public static final String X_AGENT_NAME = "x-agentname";
    public static final String X_EXECUTOR_USER = "x-executor-user";
    public static final String X_MESSAGE_TIME = "\"x-message-time\"";
    public static final String UNKNOWN_APPLICATION = "UNKNOWN_APPLICATION";
    public static final String UNKNOWN_HOSTNAME = "UNKNOWN_HOSTNAME";

    private AuditionConstants() {
        throw new IllegalStateException("Utility class");
    }
}