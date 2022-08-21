package com.review.monitoring.MonitoringSystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getDashboard() {
        return "dashboard";
    }
}
