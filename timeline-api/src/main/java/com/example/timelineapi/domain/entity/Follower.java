package com.example.timelineapi.domain.entity;

import com.example.timelineapi.domain.util.UUIDUtils;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "followers")
@Entity(name = "followers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Follower {
    @Id
    @Builder.Default
    private String id = UUIDUtils.random().toString();
    private String followingUserId;
    private String followedUserId;
}
