package com.example.timelineapi.infrastructure.interceptor;

import com.example.timelineapi.domain.exception.BusinessException;
import com.example.timelineapi.domain.exception.DomainNotFoundException;
import com.example.timelineapi.infrastructure.exception.InvalidRequestException;
import com.example.timelineapi.infrastructure.exception.RestTemplateException;
import com.example.timelineapi.infrastructure.model.ErrorDetail;
import com.example.timelineapi.infrastructure.model.RestErrorMessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ERROR_MESSAGE_SPLITTER = ";";
    private static final String REQUEST_VALIDATION_EXCEPTION_USER_MESSAGE = "Unexpected error. Please report to example@example.com ";
    private static final String INTERNAL_SERVER_EXCEPTION_USER_MESSAGE = "An unknown exception has occurred. Please report to example@example.com";

    private final MessageSource messageSource;


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        log.warn("An invalid api call occurred: ", exception);
        final String messageKey = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        final RestErrorMessageResponse error = createErrorResponse(REQUEST_VALIDATION_EXCEPTION_USER_MESSAGE, messageKey, (Object) null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleRuntimeException(final Exception exception) {
        logger.error("A runtime exception has occurred: ", exception);
        final RestErrorMessageResponse error = createErrorResponse(INTERNAL_SERVER_EXCEPTION_USER_MESSAGE, "internal.server.error", (Object) null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(RestTemplateException.class)
    public ResponseEntity<Object> handleRestTemplateException(final RestTemplateException exception) {
        logger.error("A runtime exception has occurred: ", exception);
        final RestErrorMessageResponse error = createErrorResponse(INTERNAL_SERVER_EXCEPTION_USER_MESSAGE, "rest.template.error", (Object) null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(final BusinessException exception) {
        logger.error("A business exception has occurred: ", exception);
        final RestErrorMessageResponse error = createErrorResponse(REQUEST_VALIDATION_EXCEPTION_USER_MESSAGE, exception.getKey(), (Object) null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DomainNotFoundException.class)
    public ResponseEntity<Object> handleDomainNotFoundException(final DomainNotFoundException exception) {
        logger.error("A domain exception has occurred: ", exception);
        final RestErrorMessageResponse error = createErrorResponse(REQUEST_VALIDATION_EXCEPTION_USER_MESSAGE, exception.getKey(), exception.getArgs());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Object> handleInvalidRequestException(final InvalidRequestException exception) {
        logger.error("An invalid api call has occurred: ", exception);
        final RestErrorMessageResponse error = createErrorResponse(REQUEST_VALIDATION_EXCEPTION_USER_MESSAGE, exception.getMessage(), (Object) null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private RestErrorMessageResponse createErrorResponse(final String userMessage, final String messageKey, final Object... args) {
        final String message = messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale());
        final Map<String, String> messageKeyMap = retrieveErrorCodeAndMessagePair(message);

        final var errorCode = Integer.parseInt(messageKeyMap.get("errorCode"));
        final String errorMessage = messageKeyMap.get("errorMessage");

        final var errorDetail = ErrorDetail.builder()
                .key(messageKey)
                .errorCode(String.valueOf(errorCode))
                .message(errorMessage)
                .build();
        return RestErrorMessageResponse.builder()
                .code(errorCode)
                .message(errorMessage)
                .userMessage(userMessage)
                .errors(List.of(errorDetail))
                .build();
    }

    private Map<String, String> retrieveErrorCodeAndMessagePair(final String message) {
        final String[] errorCodeMessage = message.split(ERROR_MESSAGE_SPLITTER);
        return Map.of("errorCode", errorCodeMessage[0], "errorMessage", errorCodeMessage[1]);
    }
}