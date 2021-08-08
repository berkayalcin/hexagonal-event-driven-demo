package com.example.tweetapi.domain.converter;

import com.example.tweetapi.domain.entity.User;
import com.example.tweetapi.domain.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UserToDTOConverter implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(final User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
