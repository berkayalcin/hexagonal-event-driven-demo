package com.example.timelineapi.infrastructure.rest;

import com.example.timelineapi.application.controller.MonitoringController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/_monitoring")
public class RestMonitoringController implements MonitoringController {

    @Override
    @GetMapping("/live")
    public String live() {
        return "OK";
    }

    @Override
    @GetMapping("/ready")
    public String ready() {
        return "OK";
    }
}