package com.example.timelineapi.application.mapper;

import com.example.timelineapi.application.model.response.TimelineResponse;
import com.example.timelineapi.domain.model.dto.TimelineDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TimelineResponseMapper implements Converter<TimelineDTO, TimelineResponse>, Function<TimelineDTO, TimelineResponse> {
    private final TimelineActivityResponseMapper timelineActivityResponseMapper;

    @Override
    public TimelineResponse apply(final TimelineDTO timelineDTO) {
        return convert(timelineDTO);
    }

    @Override
    public TimelineResponse convert(final TimelineDTO timelineDTO) {
        final var activityResponses = timelineDTO.getActivities().stream()
                .map(timelineActivityResponseMapper)
                .collect(Collectors.toList());
        return TimelineResponse.builder()
                .activities(activityResponses)
                .build();
    }
}
