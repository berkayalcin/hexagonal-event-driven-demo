package com.example.tweetapi.domain.converter;

import com.example.tweetapi.domain.entity.Tweet;
import com.example.tweetapi.domain.model.dto.TweetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TweetToDTOConverter implements Function<Tweet, TweetDTO> {
    @Override
    public TweetDTO apply(final Tweet tweet) {
        return TweetDTO.builder()
                .body(tweet.getBody())
                .id(tweet.getId())
                .userId(tweet.getUserId())
                .publishedAt(tweet.getPublishedAt())
                .build();
    }
}
