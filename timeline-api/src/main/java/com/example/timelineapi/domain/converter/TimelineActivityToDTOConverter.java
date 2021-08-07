package com.example.timelineapi.domain.converter;

import com.example.timelineapi.domain.entity.TimelineActivity;
import com.example.timelineapi.domain.model.dto.TimelineActivityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TimelineActivityToDTOConverter implements Function<TimelineActivity, TimelineActivityDTO> {
    @Override
    public TimelineActivityDTO apply(final TimelineActivity timelineActivity) {
        return TimelineActivityDTO.builder()
                .id(timelineActivity.getId())
                .timelineOwnerId(timelineActivity.getTimelineOwnerId())
                .publishedAt(timelineActivity.getPublishedAt())
                .tweetBody(timelineActivity.getTweetBody())
                .tweetId(timelineActivity.getTweetId())
                .tweetOwnerId(timelineActivity.getTweetOwnerId())
                .tweetOwnerName(timelineActivity.getTweetOwnerName())
                .tweetPublishedAt(timelineActivity.getTweetPublishedAt())
                .build();
    }
}
