package com.review.monitoring.MonitoringSystem.review;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewController {
    @GetMapping("/")
    public String getDashboard() {
        return "dashboard";
    }

    @GetMapping("/review")
    public String getReviewForm() {
        return "reviewForm";
    }
}
