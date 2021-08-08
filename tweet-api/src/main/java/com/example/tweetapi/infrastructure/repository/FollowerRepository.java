package com.example.tweetapi.infrastructure.repository;

import com.example.tweetapi.domain.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, String> {
    List<Follower> findAllByFollowedUserId(final String followedUserId);

    List<Follower> findAllByFollowingUserId(final String followingUserId);
}
