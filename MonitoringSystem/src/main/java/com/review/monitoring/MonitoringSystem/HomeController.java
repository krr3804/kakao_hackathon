package com.review.monitoring.MonitoringSystem;

import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.monitor.domain.CurrentUser;
import com.review.monitoring.MonitoringSystem.monitor.user.session.SessionConstants;
import com.review.monitoring.MonitoringSystem.monitor.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/manager/dashboard")
    public String getDashboard(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) MemberVO loginUser, Model model) {
        model.addAttribute("user", loginUser);
        return "dashboard";
    }

    @GetMapping("/")
    public String getHome(@AuthenticationPrincipal Member member, Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return "home";
        }
        UserDetails member2 = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("name", member2.getUsername());

        return "home";
    }
}
