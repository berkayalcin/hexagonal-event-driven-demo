package com.example.timelineapi.domain.converter;

import com.example.timelineapi.domain.entity.TimelineActivity;
import com.example.timelineapi.domain.model.dto.TimelineActivityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TimelineActivityDTOToEntityConverter implements Function<TimelineActivityDTO, TimelineActivity> {
    @Override
    public TimelineActivity apply(final TimelineActivityDTO timelineActivityDTO) {
        return TimelineActivity.builder()
                .timelineOwnerId(timelineActivityDTO.getTimelineOwnerId())
                .publishedAt(timelineActivityDTO.getPublishedAt())
                .tweetBody(timelineActivityDTO.getTweetBody())
                .tweetId(timelineActivityDTO.getTweetId())
                .tweetOwnerId(timelineActivityDTO.getTweetOwnerId())
                .tweetOwnerName(timelineActivityDTO.getTweetOwnerName())
                .tweetPublishedAt(timelineActivityDTO.getTweetPublishedAt())
                .build();
    }
}
