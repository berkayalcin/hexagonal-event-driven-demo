package com.example.tweetapi.application.mapper;

import com.example.tweetapi.application.model.response.TweetResponse;
import com.example.tweetapi.domain.model.dto.TweetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TweetResponseMapper implements Function<TweetDTO, TweetResponse> {
    @Override
    public TweetResponse apply(final TweetDTO tweetDTO) {
        return TweetResponse.builder()
                .body(tweetDTO.getBody())
                .id(tweetDTO.getId())
                .publishedAt(tweetDTO.getPublishedAt())
                .userId(tweetDTO.getUserId())
                .userName(tweetDTO.getUserName())
                .build();
    }
}
