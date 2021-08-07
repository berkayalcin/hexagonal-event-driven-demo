package com.example.timelineapi.domain.model.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimelineDTO {
    private List<TimelineActivityDTO> activities;
}
