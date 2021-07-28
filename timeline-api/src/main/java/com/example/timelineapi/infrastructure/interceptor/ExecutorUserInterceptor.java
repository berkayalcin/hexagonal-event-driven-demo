package com.example.timelineapi.infrastructure.interceptor;

import com.example.timelineapi.infrastructure.constant.AuditionConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ExecutorUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String executorUser = Optional
                .ofNullable(request.getHeader(AuditionConstants.X_EXECUTOR_USER))
                .map(Object::toString)
                .orElseGet(() -> Optional
                        .ofNullable(request.getAttribute(AuditionConstants.X_EXECUTOR_USER))
                        .map(Object::toString)
                        .orElse(StringUtils.EMPTY));
        MDC.put(AuditionConstants.X_EXECUTOR_USER, executorUser);
        //NewRelic.addCustomParameter(AuditionConstants.X_EXECUTOR_USER, executorUser);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        MDC.remove(AuditionConstants.X_EXECUTOR_USER);
    }
}