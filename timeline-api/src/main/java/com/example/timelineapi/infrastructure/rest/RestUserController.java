package com.example.timelineapi.infrastructure.rest;

import com.example.timelineapi.application.controller.UserController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class RestUserController implements UserController {
    @Override
    public void get() {

    }
}
