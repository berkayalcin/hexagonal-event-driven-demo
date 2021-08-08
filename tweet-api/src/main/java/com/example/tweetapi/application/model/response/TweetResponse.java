package com.example.tweetapi.application.model.response;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class TweetResponse {
    String id;
    String userId;
    String body;
    Date publishedAt;
    String userName;
}
