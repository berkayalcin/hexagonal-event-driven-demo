package com.example.timelineapi.infrastructure.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LogExecutionInterceptor implements HandlerInterceptor {

    private static final String REQUEST_START_TIME = "requestStartTime";

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        try {
            if (handler instanceof HandlerMethod) {
                final var handlerMethod = (HandlerMethod) handler;
                final String controllerName = ClassUtils.getShortClassName(handlerMethod.getBean().getClass());
                if (checkIfNotExcludedForLogging(controllerName)) {
                    request.setAttribute(REQUEST_START_TIME, System.currentTimeMillis());
                    log.info("Starting controller method for {}.{}",
                            controllerName,
                            handlerMethod.getMethod().getName());
                }
            }
        } catch (Exception e) {
            log.error("Caught an exception while executing handler method", e);
        }
        return true;
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) {
        try {
            if (handler instanceof HandlerMethod) {
                final var handlerMethod = (HandlerMethod) handler;
                final String controllerName = ClassUtils.getShortClassName(handlerMethod.getBean().getClass());
                if (checkIfNotExcludedForLogging(controllerName)) {
                    log.info("Completed controller method for {}.{} takes {} ms",
                            ClassUtils.getShortClassName(handlerMethod.getBean().getClass()),
                            handlerMethod.getMethod().getName(),
                            System.currentTimeMillis() - (Long) request.getAttribute(REQUEST_START_TIME));
                }
            }
        } catch (Exception e) {
            log.error("Caught an exception while executing handler method", e);
        }
    }

    private boolean checkIfNotExcludedForLogging(final String controllerName) {
        return !controllerName.equals("RestMonitoringController");
    }
}