package com.example.tweetapi.application.model.request;

import lombok.*;

import java.util.Date;

@Value
@Builder
public class CreateTweetRequest {
    String id;
    String userId;
    String body;
    Date publishedAt;
}
