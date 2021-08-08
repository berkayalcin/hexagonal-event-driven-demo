package com.example.tweetapi.domain.model.dto;

import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TweetDTO {
    private String id;
    private String userId;
    private String body;
    private Date publishedAt;
    private String userName;
}
