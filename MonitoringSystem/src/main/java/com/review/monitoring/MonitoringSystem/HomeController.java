package com.review.monitoring.MonitoringSystem;

import com.review.monitoring.MonitoringSystem.monitor.user.session.SessionConstants;
import com.review.monitoring.MonitoringSystem.monitor.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/")
    public String getDashboard(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) MemberVO loginUser, Model model) {
        if(loginUser == null) {
            return "home";
        }
        model.addAttribute("user",loginUser);
        return "dashboard";
    }
}
