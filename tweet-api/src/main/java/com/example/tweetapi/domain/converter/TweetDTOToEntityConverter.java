package com.example.tweetapi.domain.converter;

import com.example.tweetapi.domain.entity.Tweet;
import com.example.tweetapi.domain.model.dto.TweetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TweetDTOToEntityConverter implements Function<TweetDTO, Tweet> {
    @Override
    public Tweet apply(final TweetDTO tweetDTO) {
        return Tweet.builder()
                .body(tweetDTO.getBody())
                .publishedAt(tweetDTO.getPublishedAt())
                .userId(tweetDTO.getUserId())
                .build();
    }
}
