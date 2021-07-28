package com.example.timelineapi.domain.model.dto;

import com.example.timelineapi.domain.util.UUIDUtils;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
