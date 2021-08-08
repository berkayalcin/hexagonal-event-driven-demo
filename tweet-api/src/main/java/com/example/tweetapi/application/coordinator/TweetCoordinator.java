package com.example.tweetapi.application.coordinator;

import com.example.tweetapi.application.converter.CreateTweetRequestToDTOConverter;
import com.example.tweetapi.application.mapper.TweetResponseMapper;
import com.example.tweetapi.application.model.request.CreateTweetRequest;
import com.example.tweetapi.application.model.response.TweetResponse;
import com.example.tweetapi.domain.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TweetCoordinator {
    private final TweetService tweetService;
    private final CreateTweetRequestToDTOConverter createTweetRequestToDTOConverter;
    private final TweetResponseMapper tweetResponseMapper;

    public TweetResponse create(final CreateTweetRequest createTweetRequest) {
        final var tweetDTO = createTweetRequestToDTOConverter.convert(createTweetRequest);
        final var result = tweetService.create(tweetDTO);
        return tweetResponseMapper.apply(result);
    }
}
