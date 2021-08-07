package com.example.timelineapi.domain.model.dto;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimelineActivityDTO {
    private String id;
    private String timelineOwnerId;
    private String tweetId;
    private String tweetBody;
    private String tweetOwnerId;
    private String tweetOwnerName;
    private Date tweetPublishedAt;
    private Date publishedAt;
}
