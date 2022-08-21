package com.review.monitoring.MonitoringSystem.review;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("")
    public String getReviewForm() {
        return "reviewForm";
    }

    @PostMapping("/new")
    public String registerReview(Review review) {
        review.setDate(LocalDateTime.now());

        if(reviewService.writeReview(review) == null) {
            return "redirect:/review";
        }
        return "redirect:/";
    }
}
