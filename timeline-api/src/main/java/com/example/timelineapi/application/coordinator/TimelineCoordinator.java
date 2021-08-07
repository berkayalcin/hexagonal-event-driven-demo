package com.example.timelineapi.application.coordinator;

import com.example.timelineapi.application.converter.SeedTimelineEventToTimelineActivityDTOConverter;
import com.example.timelineapi.application.mapper.TimelineResponseMapper;
import com.example.timelineapi.application.model.response.TimelineResponse;
import com.example.timelineapi.application.model.event.SeedTimelineEvent;
import com.example.timelineapi.domain.service.TimelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TimelineCoordinator {
    private final TimelineService timelineService;
    private final TimelineResponseMapper timelineResponseMapper;
    private final SeedTimelineEventToTimelineActivityDTOConverter seedTimelineEventToTimelineActivityDTOConverter;

    public TimelineResponse getByOwnerId(final String ownerId, final int page) {
        final var timelineDTO = timelineService.getByOwner(ownerId, page);
        return timelineResponseMapper.convert(timelineDTO);
    }

    public void seedTimeline(final SeedTimelineEvent seedTimelineEvent) {
        final var timelineActivityDTO = seedTimelineEventToTimelineActivityDTOConverter.apply(seedTimelineEvent);
        timelineService.seedTimeline(timelineActivityDTO);
    }
}
