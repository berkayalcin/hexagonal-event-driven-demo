package com.example.timelineapi.application.controller;

import com.example.timelineapi.application.model.response.TimelineResponse;

public interface TimelineController {
    TimelineResponse getByOwnerId(final String timelineOwnerId, final int page);
}
