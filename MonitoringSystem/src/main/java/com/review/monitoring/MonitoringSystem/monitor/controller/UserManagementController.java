package com.review.monitoring.MonitoringSystem.monitor.controller;

import com.review.monitoring.MonitoringSystem.monitor.domain.Department;
import com.review.monitoring.MonitoringSystem.monitor.domain.User;
import com.review.monitoring.MonitoringSystem.monitor.service.UserManagementService;
import com.review.monitoring.MonitoringSystem.monitor.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserManagementController {
    private final UserManagementService userManagementService;

    @GetMapping("")
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/login")
    public @ResponseBody String login(String userId, String password) {
        User user = userManagementService.logIn(userId, password);
        if(user == null) {
            return "fail";
        }
        return "success";
    }

    @GetMapping("/registerForm")
    public String registerForm() {
        return "registerForm";
    }

    @PostMapping("/userIdCheck")
    public @ResponseBody String userIdCheck(String userId) {
        int result = userManagementService.checkDuplicateUser(userId);
        if(result != 0) {
            return "fail";
        }
        return "success";
    }

    @PostMapping("/register")
    public String register(Model model, UserVO userVO) {
        User user = new User(userVO.getId(),userVO.getPassword(), userVO.getEmail(),Department.of(userVO.getDepartment()));
        userManagementService.register(user);

        return "redirect:/";
    }
}
