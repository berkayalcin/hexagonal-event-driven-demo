package com.example.timelineapi.infrastructure.repository;

import com.example.timelineapi.domain.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower, String> {
}
