package com.example.timelineapi.domain.event;

import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SeedTimelineEvent {
    private String timelineOwnerId;
    private String tweetId;
    private String tweetBody;
    private String tweetOwnerId;
    private String tweetOwnerName;
    private Date tweetPublishedAt;
    private Date publishedAt;
}
