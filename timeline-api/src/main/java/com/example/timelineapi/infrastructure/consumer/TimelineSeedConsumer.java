package com.example.timelineapi.infrastructure.consumer;

import com.example.timelineapi.domain.event.SeedTimelineEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class TimelineSeedConsumer {
    @KafkaListener(topics = "timeline.seed", groupId = "timeline.service")
    @SneakyThrows
    public void listen(@Payload String event) {
        final var objectMapper = new ObjectMapper();
        final var seedTimelineEvent = objectMapper.readValue(event, SeedTimelineEvent.class);
    }
}
