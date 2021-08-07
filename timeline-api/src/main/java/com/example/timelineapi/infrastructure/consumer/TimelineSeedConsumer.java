package com.example.timelineapi.infrastructure.consumer;

import com.example.timelineapi.application.coordinator.TimelineCoordinator;
import com.example.timelineapi.application.model.event.SeedTimelineEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimelineSeedConsumer {
    private final TimelineCoordinator timelineCoordinator;

    @KafkaListener(topics = "timeline.seed", groupId = "timeline.service")
    @SneakyThrows
    public void listen(@Payload String event) {
        final var objectMapper = new ObjectMapper();
        final var seedTimelineEvent = objectMapper.readValue(event, SeedTimelineEvent.class);
        timelineCoordinator.seedTimeline(seedTimelineEvent);
    }
}
