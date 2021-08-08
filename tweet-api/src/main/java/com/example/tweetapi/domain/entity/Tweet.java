package com.example.tweetapi.domain.entity;

import com.example.tweetapi.infrastructure.util.UUIDUtils;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tweets")
@Entity(name = "tweets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tweet {
    @Id
    @Builder.Default
    private String id = UUIDUtils.random().toString();
    private String userId;
    private String body;
    private Date publishedAt;
}
