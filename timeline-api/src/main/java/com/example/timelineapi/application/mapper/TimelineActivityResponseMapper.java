package com.example.timelineapi.application.mapper;

import com.example.timelineapi.application.model.response.TimelineActivityResponse;
import com.example.timelineapi.domain.model.dto.TimelineActivityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TimelineActivityResponseMapper implements Function<TimelineActivityDTO, TimelineActivityResponse>, Converter<TimelineActivityDTO, TimelineActivityResponse> {
    @Override
    public TimelineActivityResponse apply(final TimelineActivityDTO timelineActivityDTO) {
        return convert(timelineActivityDTO);
    }

    @Override
    public TimelineActivityResponse convert(final TimelineActivityDTO timelineActivity) {
        return TimelineActivityResponse.builder()
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
