package com.example.tweetapi.domain.entity;


import com.example.tweetapi.infrastructure.util.UUIDUtils;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "users")
@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Builder.Default
    private String id = UUIDUtils.random().toString();
    private String firstName;
    private String lastName;
    private String email;
}
