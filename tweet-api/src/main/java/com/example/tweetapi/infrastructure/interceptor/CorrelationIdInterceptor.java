package com.example.tweetapi.infrastructure.interceptor;

import com.example.tweetapi.infrastructure.util.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.tweetapi.infrastructure.constant.AuditionConstants.X_AGENT_NAME;
import static com.example.tweetapi.infrastructure.constant.AuditionConstants.X_CORRELATION_ID;


public class CorrelationIdInterceptor implements HandlerInterceptor {

    private final String applicationName;

    public CorrelationIdInterceptor(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String correlationId = request.getHeader(X_CORRELATION_ID);
        String agentName = request.getHeader(X_AGENT_NAME);

        if (StringUtils.isBlank(correlationId)) {
            correlationId = UUIDUtils.random().toString();
        }

        if (StringUtils.isBlank(agentName)) {
            agentName = applicationName;
        }

        MDC.put(X_CORRELATION_ID, correlationId);
        MDC.put(X_AGENT_NAME, agentName);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        MDC.remove(X_CORRELATION_ID);
        MDC.remove(X_AGENT_NAME);
    }
}