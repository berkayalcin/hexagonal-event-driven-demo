package com.example.tweetapi.domain.service;

import com.example.tweetapi.domain.converter.UserToDTOConverter;
import com.example.tweetapi.domain.model.dto.UserDTO;
import com.example.tweetapi.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserToDTOConverter userToDTOConverter;

    //@Cacheable(cacheNames = "user", key = "'user#' + #id")
    @SneakyThrows
    public UserDTO findById(final String id) {
        final var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new Exception();
        }
        return userToDTOConverter.apply(user.get());
    }

}
