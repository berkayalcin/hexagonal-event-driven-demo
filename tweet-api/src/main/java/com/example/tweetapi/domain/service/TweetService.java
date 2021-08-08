package com.example.tweetapi.domain.service;

import com.example.tweetapi.domain.constant.TopicConstants;
import com.example.tweetapi.domain.converter.TweetDTOToEntityConverter;
import com.example.tweetapi.domain.entity.Tweet;
import com.example.tweetapi.domain.model.dto.FollowerDTO;
import com.example.tweetapi.domain.model.dto.TweetDTO;
import com.example.tweetapi.domain.model.dto.UserDTO;
import com.example.tweetapi.domain.model.event.SeedTimelineEvent;
import com.example.tweetapi.infrastructure.producer.KafkaProducer;
import com.example.tweetapi.infrastructure.repository.TweetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TweetService {
    private final TweetRepository tweetRepository;
    private final FollowerService followerService;
    private final UserService userService;
    private final TweetDTOToEntityConverter tweetDTOToEntityConverter;
    private final KafkaProducer kafkaProducer;

    @Transactional
    public TweetDTO create(final TweetDTO tweetDTO) {
        final var tweet = persistTweet(tweetDTO);
        seedTimelineOfFollowers(tweet);
        return tweetDTO;
    }

    private Tweet persistTweet(final TweetDTO tweetDTO) {
        final var tweet = tweetDTOToEntityConverter.apply(tweetDTO);
        tweetRepository.save(tweet);
        tweetDTO.setId(tweet.getId());
        return tweet;
    }

    private void seedTimelineOfFollowers(final Tweet tweet) {
        final var followers = followerService.findAllByFollowedUserId(tweet.getUserId());
        followers
                .stream()
                .parallel()
                .forEachOrdered(f -> seedTimelineOfFollower(tweet, f));
    }

    @SneakyThrows
    private void seedTimelineOfFollower(final Tweet tweet, final FollowerDTO follower) {
        final var seedTimelineEvent = buildSeedTimelineEvent(tweet, follower);
        final var objectMapper = new ObjectMapper();
        final var event = objectMapper.writeValueAsString(seedTimelineEvent);
        kafkaProducer.sendMessage(TopicConstants.SEED_TIMELINE_TOPIC, event);
    }

    private SeedTimelineEvent buildSeedTimelineEvent(final Tweet tweet, final FollowerDTO followerDTO) {
        final UserDTO tweetOwner = userService.findById(tweet.getUserId());
        return SeedTimelineEvent.builder()
                .timelineOwnerId(followerDTO.getFollowingUserId())
                .tweetBody(tweet.getBody())
                .tweetId(tweet.getId())
                .tweetOwnerId(tweet.getUserId())
                .tweetOwnerName(StringUtils.join(tweetOwner.getFirstName(), StringUtils.SPACE, tweetOwner.getLastName()))
                .publishedAt(tweet.getPublishedAt())
                .tweetPublishedAt(tweet.getPublishedAt())
                .build();
    }
}
