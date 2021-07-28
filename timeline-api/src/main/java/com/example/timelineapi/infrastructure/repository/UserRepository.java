package com.example.timelineapi.infrastructure.repository;

import com.example.timelineapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
