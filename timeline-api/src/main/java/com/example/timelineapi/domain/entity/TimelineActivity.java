package com.example.timelineapi.domain.entity;

import com.example.timelineapi.domain.util.UUIDUtils;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "timeline_activities")
@Entity(name = "timeline_activities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimelineActivity {
    @Id
    @Builder.Default
    private String id = UUIDUtils.random().toString();
    private String timelineOwnerId;
    private String tweetId;
    private String tweetBody;
    private String tweetOwnerId;
    private String tweetOwnerName;
    private Date tweetPublishedAt;
    private Date publishedAt;
}
