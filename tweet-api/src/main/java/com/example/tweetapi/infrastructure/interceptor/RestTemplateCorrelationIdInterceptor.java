package com.example.tweetapi.infrastructure.interceptor;

import com.example.tweetapi.infrastructure.constant.AuditionConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestTemplateCorrelationIdInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String correlationId = MDC.get(AuditionConstants.X_CORRELATION_ID);
        if (StringUtils.isNotBlank(correlationId)) {
            request.getHeaders().add(AuditionConstants.X_CORRELATION_ID, correlationId);
        }

        String agentName = MDC.get(AuditionConstants.X_AGENT_NAME);
        if (StringUtils.isNotBlank(agentName)) {
            request.getHeaders().add(AuditionConstants.X_AGENT_NAME, agentName);
        }

        String executorUser = MDC.get(AuditionConstants.X_EXECUTOR_USER);
        if (StringUtils.isNotBlank(executorUser)) {
            request.getHeaders().add(AuditionConstants.X_EXECUTOR_USER, executorUser);
        }

        return execution.execute(request, body);
    }
}