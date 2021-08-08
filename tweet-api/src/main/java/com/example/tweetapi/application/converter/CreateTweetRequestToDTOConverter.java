package com.example.tweetapi.application.converter;

import com.example.tweetapi.application.model.request.CreateTweetRequest;
import com.example.tweetapi.domain.model.dto.TweetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CreateTweetRequestToDTOConverter implements Converter<CreateTweetRequest, TweetDTO>, Function<CreateTweetRequest, TweetDTO> {
    @Override
    public TweetDTO apply(final CreateTweetRequest createTweetRequest) {
        return convert(createTweetRequest);
    }

    @Override
    public TweetDTO convert(final CreateTweetRequest createTweetRequest) {
        return TweetDTO.builder()
                .publishedAt(createTweetRequest.getPublishedAt())
                .id(createTweetRequest.getId())
                .body(createTweetRequest.getBody())
                .userId(createTweetRequest.getUserId())
                .build();
    }
}
