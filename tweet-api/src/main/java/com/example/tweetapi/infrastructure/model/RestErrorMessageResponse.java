package com.example.tweetapi.infrastructure.model;

import com.example.tweetapi.infrastructure.model.ErrorDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class RestErrorMessageResponse {
    private int code;
    private String message;
    private String userMessage;
    @Builder.Default
    private List<ErrorDetail> errors = new ArrayList<>();
}