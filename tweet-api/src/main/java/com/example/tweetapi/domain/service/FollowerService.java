package com.example.tweetapi.domain.service;

import com.example.tweetapi.domain.converter.FollowerToDTOConverter;
import com.example.tweetapi.domain.model.dto.FollowerDTO;
import com.example.tweetapi.infrastructure.repository.FollowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowerService {
    private final FollowerRepository followerRepository;
    private final FollowerToDTOConverter followerToDTOConverter;

    public List<FollowerDTO> findAllByFollowedUserId(final String followedUserId) {
        final var followers = followerRepository.findAllByFollowedUserId(followedUserId);
        return followers.stream().map(followerToDTOConverter).collect(Collectors.toList());
    }

    public List<FollowerDTO> findAllByFollowingUserId(final String followingUserId) {
        final var followers = followerRepository.findAllByFollowingUserId(followingUserId);
        return followers.stream().map(followerToDTOConverter).collect(Collectors.toList());
    }
}
