package com.review.monitoring.MonitoringSystem.monitor.user.controller;

import com.review.monitoring.MonitoringSystem.monitor.domain.Department;
import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.monitor.user.service.MemberService;
import com.review.monitoring.MonitoringSystem.monitor.user.session.SessionConstants;
import com.review.monitoring.MonitoringSystem.monitor.vo.MemberVO;
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
public class MemberRegistrationController {
    private final MemberService memberService;

    @GetMapping("/registerForm")
    public String registerForm() {
        return "registerForm";
    }

    @PostMapping("/memberIdCheck")
    public @ResponseBody String memberIdCheck(String memberId) {
        int result = memberService.checkDuplicateMember(memberId);
        if(result != 0) {
            return "fail";
        }
        return "success";
    }

    @PostMapping("/register")
    public String register(HttpServletRequest request,
                           @Validated @ModelAttribute MemberVO memberVO,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("errors = {}",bindingResult);
            return "redirect:/registerForm";
        }

        Member member = new Member(memberVO.getId(), memberVO.getPassword(), memberVO.getEmail(), Department.of(memberVO.getDepartment()));
        memberService.register(member);
        memberVO.setPassword(null);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(SessionConstants.LOGIN_MEMBER, memberVO);
        return "redirect:/";
    }
}
