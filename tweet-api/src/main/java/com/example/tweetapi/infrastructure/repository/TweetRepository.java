package com.example.tweetapi.infrastructure.repository;

import com.example.tweetapi.domain.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, String> {
}
