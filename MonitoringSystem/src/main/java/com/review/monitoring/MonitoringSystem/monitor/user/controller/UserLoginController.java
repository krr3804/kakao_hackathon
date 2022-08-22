package com.review.monitoring.MonitoringSystem.monitor.user.controller;

import com.review.monitoring.MonitoringSystem.monitor.domain.User;
import com.review.monitoring.MonitoringSystem.monitor.user.service.UserManagementService;
import com.review.monitoring.MonitoringSystem.monitor.user.session.SessionConstants;
import com.review.monitoring.MonitoringSystem.monitor.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserLoginController {
    private final UserManagementService userManagementService;

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/login")
    public @ResponseBody String login(String userId, String password, HttpServletRequest request) {
        User user = userManagementService.logIn(userId, password);
        if(user == null) {
            return "fail";
        }
        UserVO userVO = new UserVO(user.getId(),user.getEmail(),user.getDepartment().getName());

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(SessionConstants.LOGIN_USER,user);
        return "success";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
