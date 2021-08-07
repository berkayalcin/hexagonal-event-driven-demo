package com.example.timelineapi.application.model.response;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class TimelineResponse {
    private List<TimelineActivityResponse> activities;
}
