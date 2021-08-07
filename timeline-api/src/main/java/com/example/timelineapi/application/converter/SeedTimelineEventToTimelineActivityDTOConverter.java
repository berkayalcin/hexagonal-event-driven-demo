package com.example.timelineapi.application.converter;

import com.example.timelineapi.application.model.event.SeedTimelineEvent;
import com.example.timelineapi.domain.model.dto.TimelineActivityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class SeedTimelineEventToTimelineActivityDTOConverter implements Function<SeedTimelineEvent, TimelineActivityDTO> {
    @Override
    public TimelineActivityDTO apply(final SeedTimelineEvent seedTimelineEvent) {
        return TimelineActivityDTO.builder()
                .tweetPublishedAt(seedTimelineEvent.getTweetPublishedAt())
                .tweetOwnerName(seedTimelineEvent.getTweetOwnerName())
                .timelineOwnerId(seedTimelineEvent.getTimelineOwnerId())
                .tweetOwnerId(seedTimelineEvent.getTweetOwnerId())
                .tweetId(seedTimelineEvent.getTweetId())
                .tweetBody(seedTimelineEvent.getTweetBody())
                .publishedAt(seedTimelineEvent.getPublishedAt())
                .build();
    }
}
