package com.example.tweetapi.infrastructure.producer;

import com.example.tweetapi.domain.constant.TopicConstants;
import com.example.tweetapi.domain.entity.Tweet;
import com.example.tweetapi.domain.model.dto.FollowerDTO;
import com.example.tweetapi.domain.model.dto.TweetDTO;
import com.example.tweetapi.domain.model.dto.UserDTO;
import com.example.tweetapi.domain.model.event.SeedTimelineEvent;
import com.example.tweetapi.domain.service.FollowerService;
import com.example.tweetapi.domain.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeedTimelineProducer {
    private final FollowerService followerService;
    private final UserService userService;
    private final KafkaProducer kafkaProducer;

    @Async(value = "singleThreadedPoolBean")
    public void seedTimelineOfFollowers(final TweetDTO tweet) {
        // NOTES : Instead of publishing message here, we can create an event that commands to publish these messages, and send
        // this event to Timeline-Seeder-Service.
        final var followers = followerService.findAllByFollowedUserId(tweet.getUserId());
        followers
                .stream()
                .parallel()
                .forEachOrdered(f -> seedTimelineOfFollower(tweet, f));
    }

    @SneakyThrows
    private void seedTimelineOfFollower(final TweetDTO tweet, final FollowerDTO follower) {
        final var seedTimelineEvent = buildSeedTimelineEvent(tweet, follower);
        final var objectMapper = new ObjectMapper();
        final var event = objectMapper.writeValueAsString(seedTimelineEvent);
        kafkaProducer.sendMessage(TopicConstants.SEED_TIMELINE_TOPIC, event);
    }

    private SeedTimelineEvent buildSeedTimelineEvent(final TweetDTO tweet, final FollowerDTO followerDTO) {
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
