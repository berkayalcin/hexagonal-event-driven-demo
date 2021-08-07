package com.example.timelineapi.infrastructure.repository;

import com.example.timelineapi.domain.entity.TimelineActivity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimelineActivityRepository extends JpaRepository<TimelineActivity, String> {
    List<TimelineActivity> findByTimelineOwnerIdOrderByPublishedAtDesc(final String timelineOwnerId, final Pageable pageable);
}
