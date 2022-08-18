package com.review.monitoring.MonitoringSystem.review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @GetMapping("/")
    public String getDashboard() {
        return "dashboard";
    }

    @GetMapping("/review")
    public String getReviewForm() {
        return "reviewForm";
    }

    @PostMapping("/review/new")
    public String registerReview(Review review) {
        if(reviewService.writeReview(review) == null) {
            return "redirect:/review";
        }
        return "dashboard";
    }
}
