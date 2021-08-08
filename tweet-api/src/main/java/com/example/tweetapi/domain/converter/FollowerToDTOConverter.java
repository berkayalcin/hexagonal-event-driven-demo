package com.example.tweetapi.domain.converter;

import com.example.tweetapi.domain.entity.Follower;
import com.example.tweetapi.domain.model.dto.FollowerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class FollowerToDTOConverter implements Function<Follower, FollowerDTO> {
    @Override
    public FollowerDTO apply(final Follower follower) {
        return FollowerDTO.builder()
                .followedUserId(follower.getFollowedUserId())
                .followingUserId(follower.getFollowingUserId())
                .id(follower.getId())
                .build();
    }
}
