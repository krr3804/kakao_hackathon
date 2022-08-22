package com.review.monitoring.MonitoringSystem.monitor.user.controller;

import com.review.monitoring.MonitoringSystem.monitor.domain.User;
import com.review.monitoring.MonitoringSystem.monitor.user.service.UserManagementService;
import com.review.monitoring.MonitoringSystem.monitor.user.session.SessionConstants;
import com.review.monitoring.MonitoringSystem.monitor.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserManagementController {
    private final UserManagementService userManagementService;

    @GetMapping("")
    public String showUserPage(@SessionAttribute(name= SessionConstants.LOGIN_USER) UserVO user, Model model) {
        if(user == null) {
            return "redirect:/";
        }
        return "user";
    }

    @GetMapping("/userInfo")
    public String showUserInfo(@SessionAttribute(name=SessionConstants.LOGIN_USER) UserVO user, Model model) {
        if(user == null) {
            return "redirect:/";
        }
        model.addAttribute("user",user);

        return "userInfo";
    }

    @PostMapping("/edit")
    public String updateUser(@SessionAttribute(name=SessionConstants.LOGIN_USER) UserVO user, @Validated @ModelAttribute UserVO updatedVO,
                             BindingResult bindingResult,HttpServletRequest request) {
        if(user == null) {
            return "redirect:/";
        }

        if(bindingResult.hasErrors()) {
            log.info("errors={}",bindingResult);
            return "redirect:/userInfo";
        }
        User updatedUser = userManagementService.updateUserInfo(updatedVO,user);
        if(updatedUser != null) {
            HttpSession session = request.getSession();
            updatedVO.setPassword(null);
            session.setAttribute(SessionConstants.LOGIN_USER,updatedVO);
        }

        return "redirect:/userInfo";
    }

    @PostMapping("/delete")
    public String deleteUser(@SessionAttribute(name=SessionConstants.LOGIN_USER) UserVO user, HttpServletRequest request) {
        if(user == null) {
            return "redirect:/";
        }
        userManagementService.delete(user.getId());

        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
