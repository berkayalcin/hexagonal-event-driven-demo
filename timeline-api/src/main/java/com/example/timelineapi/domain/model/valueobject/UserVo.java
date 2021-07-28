package com.example.timelineapi.domain.model.valueobject;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserVo {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
