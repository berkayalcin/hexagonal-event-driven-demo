package com.example.timelineapi.domain.repository;

import com.example.timelineapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
