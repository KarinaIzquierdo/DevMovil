package com.app.backend.controller;

import com.app.backend.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Re;


@RestController
@RequestMapping("/api/stast")
public class StatsController {
@Autowired
private StatsService statsService;

@GetMapping("")
public Map<String, Long> getStats () {
    return statsService.getStats();
}
}