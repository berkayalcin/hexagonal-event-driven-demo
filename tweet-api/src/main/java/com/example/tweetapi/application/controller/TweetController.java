package com.example.tweetapi.application.controller;

import com.example.tweetapi.application.model.request.CreateTweetRequest;
import com.example.tweetapi.application.model.response.TweetResponse;

public interface TweetController {
    TweetResponse create(final CreateTweetRequest createTweetRequest);
}
