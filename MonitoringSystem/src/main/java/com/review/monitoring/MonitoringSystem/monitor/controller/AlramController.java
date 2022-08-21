package com.review.monitoring.MonitoringSystem.monitor.controller;


import com.review.monitoring.MonitoringSystem.dto.AlramDTO;
import com.review.monitoring.MonitoringSystem.monitor.domain.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/alram")
public class AlramController {
    @GetMapping("/register")
    public String alramRegisterFrom(Model model ) {
        model.addAttribute("form", new AlramDTO());
        System.out.println(Keyword.values());

        model.addAttribute("keyword", Keyword.values());
        return "alramRegisterForm";
    }
}
