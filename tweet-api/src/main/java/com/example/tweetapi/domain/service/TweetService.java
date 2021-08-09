package com.example.tweetapi.domain.service;

import com.example.tweetapi.domain.converter.TweetDTOToEntityConverter;
import com.example.tweetapi.domain.entity.Tweet;
import com.example.tweetapi.domain.model.dto.TweetDTO;
import com.example.tweetapi.infrastructure.producer.SeedTimelineProducer;
import com.example.tweetapi.infrastructure.repository.TweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TweetService {
    private final TweetRepository tweetRepository;
    private final TweetDTOToEntityConverter tweetDTOToEntityConverter;
    private final SeedTimelineProducer seedTimelineProducer;

    public TweetDTO create(final TweetDTO tweetDTO) {
        persistTweet(tweetDTO);
        seedTimelineProducer.seedTimelineOfFollowers(tweetDTO);
        return tweetDTO;
    }

    private Tweet persistTweet(final TweetDTO tweetDTO) {
        final var tweet = tweetDTOToEntityConverter.apply(tweetDTO);
        tweetRepository.save(tweet);
        tweetDTO.setId(tweet.getId());
        return tweet;
    }
}
