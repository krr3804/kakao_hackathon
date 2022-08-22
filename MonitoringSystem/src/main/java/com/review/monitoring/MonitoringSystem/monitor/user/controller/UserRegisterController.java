package com.review.monitoring.MonitoringSystem.monitor.user.controller;

import com.review.monitoring.MonitoringSystem.monitor.domain.Department;
import com.review.monitoring.MonitoringSystem.monitor.domain.User;
import com.review.monitoring.MonitoringSystem.monitor.user.service.UserManagementService;
import com.review.monitoring.MonitoringSystem.monitor.user.session.SessionConstants;
import com.review.monitoring.MonitoringSystem.monitor.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserRegisterController {
    private final UserManagementService userManagementService;

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
    public String register(HttpServletRequest request,
                           @Validated @ModelAttribute UserVO userVO,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("errors = {}",bindingResult);
            return "redirect:/registerForm";
        }

        User user = new User(userVO.getId(),userVO.getPassword(), userVO.getEmail(), Department.of(userVO.getDepartment()));
        userManagementService.register(user);
        userVO.setPassword(null);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(SessionConstants.LOGIN_USER,userVO);
        return "redirect:/";
    }
}
