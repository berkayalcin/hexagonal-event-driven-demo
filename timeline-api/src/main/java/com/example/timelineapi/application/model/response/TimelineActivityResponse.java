package com.example.timelineapi.application.model.response;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class TimelineActivityResponse {
    String id;
    String timelineOwnerId;
    String tweetId;
    String tweetBody;
    String tweetOwnerId;
    String tweetOwnerName;
    Date tweetPublishedAt;
    Date publishedAt;
}
