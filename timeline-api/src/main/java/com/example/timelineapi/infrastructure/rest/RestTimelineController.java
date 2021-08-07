package com.example.timelineapi.infrastructure.rest;

import com.example.timelineapi.application.controller.TimelineController;
import com.example.timelineapi.application.coordinator.TimelineCoordinator;
import com.example.timelineapi.application.model.response.TimelineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timelines")
@RequiredArgsConstructor
public class RestTimelineController implements TimelineController {
    private final TimelineCoordinator timelineCoordinator;

    @GetMapping("/{ownerId}")
    @Override
    public TimelineResponse getByOwnerId(@PathVariable final String ownerId, @RequestParam final int page) {
        return timelineCoordinator.getByOwnerId(ownerId, page);
    }
}
