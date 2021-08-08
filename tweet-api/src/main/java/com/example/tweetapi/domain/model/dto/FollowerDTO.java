package com.example.tweetapi.domain.model.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FollowerDTO {
    private String id;
    private String followingUserId;
    private String followedUserId;
    private String followingUserName;
    private String followedUserName;
}
