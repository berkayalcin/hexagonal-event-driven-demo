package com.example.timelineapi.domain.service;

import com.example.timelineapi.domain.converter.TimelineActivityToDTOConverter;
import com.example.timelineapi.domain.model.dto.TimelineDTO;
import com.example.timelineapi.infrastructure.repository.TimelineActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimelineService {
    private final TimelineActivityRepository timelineRepository;
    private final TimelineActivityToDTOConverter timelineActivityToDTOConverter;
    private static final int PAGE_SIZE = 10;
    private static final Pageable TIMELINE_PAGINATION = Pageable.ofSize(PAGE_SIZE);

    public TimelineDTO getByOwner(final String timelineOwnerId, final int page) {
        final var pageable = TIMELINE_PAGINATION.withPage(page);
        final var timelineActivities = timelineRepository.findByTimelineOwnerIdOrderByPublishedAtDesc(timelineOwnerId, pageable);
        final var timelineActivityDTOS = timelineActivities.stream().map(timelineActivityToDTOConverter).collect(Collectors.toList());
        return TimelineDTO.builder()
                .activities(timelineActivityDTOS)
                .build();
    }

}
