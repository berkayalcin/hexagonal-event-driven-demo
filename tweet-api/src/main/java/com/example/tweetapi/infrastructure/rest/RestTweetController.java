package com.example.tweetapi.infrastructure.rest;

import com.example.tweetapi.application.controller.TweetController;
import com.example.tweetapi.application.coordinator.TweetCoordinator;
import com.example.tweetapi.application.model.request.CreateTweetRequest;
import com.example.tweetapi.application.model.response.TweetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tweets")
@RequiredArgsConstructor
public class RestTweetController implements TweetController {
    private final TweetCoordinator tweetCoordinator;

    @Override
    @PostMapping
    public TweetResponse create(@RequestBody final CreateTweetRequest createTweetRequest) {
        return tweetCoordinator.create(createTweetRequest);
    }
}
