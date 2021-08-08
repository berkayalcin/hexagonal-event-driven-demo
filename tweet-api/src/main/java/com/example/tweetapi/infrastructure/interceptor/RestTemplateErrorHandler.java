package com.example.tweetapi.infrastructure.interceptor;

import com.example.tweetapi.infrastructure.exception.RestTemplateException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
@Slf4j
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return (
                clientHttpResponse.getStatusCode().series().equals(CLIENT_ERROR)
                        || clientHttpResponse.getStatusCode().series().equals(SERVER_ERROR));
    }

    @SneakyThrows
    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        if (clientHttpResponse.getStatusCode()
                .series().equals(HttpStatus.Series.SERVER_ERROR)) {
            handleCommonError(clientHttpResponse);
        } else if (clientHttpResponse.getStatusCode()
                .series().equals(HttpStatus.Series.CLIENT_ERROR)) {
            if (clientHttpResponse.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                try {
                    log.error("Rest Template Client Error Response Body:" + IOUtils.toString(clientHttpResponse.getBody()));
                } catch (IOException e) {
                    log.error("An error has occurred while decoding rest template client response: ", ExceptionUtils.getFullStackTrace(e));
                }
                throw new RestTemplateException("internal.server.error");
            }
            handleCommonError(clientHttpResponse);
        }
    }

    private void handleCommonError(final ClientHttpResponse clientHttpResponse) throws RestTemplateException {
        try {
            log.error("Rest Template Client Error Response Body:" + IOUtils.toString(clientHttpResponse.getBody()));
        } catch (IOException e) {
            log.error("An error has occurred while decoding rest template client response: ", e);
        }
        throw new RestTemplateException("rest.template.error");
    }
}