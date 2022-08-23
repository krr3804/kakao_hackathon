package com.review.monitoring.MonitoringSystem.monitor.user.controller;

import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.monitor.user.service.MemberService;
import com.review.monitoring.MonitoringSystem.monitor.user.session.SessionConstants;
import com.review.monitoring.MonitoringSystem.monitor.vo.MemberVO;
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
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberManagementController {
    private final MemberService memberService;

    @GetMapping("")
    public String showMemberPage(@SessionAttribute(name= SessionConstants.LOGIN_MEMBER) MemberVO member, Model model) {
        if(member == null) {
            return "redirect:/";
        }
        return "member";
    }

    @GetMapping("/memberInfo")
    public String showMemberInfo(@SessionAttribute(name=SessionConstants.LOGIN_MEMBER) MemberVO member, Model model) {
        if(member == null) {
            return "redirect:/";
        }
        model.addAttribute("member",member);

        return "memberInfo";
    }

    @PostMapping("/edit")
    public String updateMember(@SessionAttribute(name=SessionConstants.LOGIN_MEMBER) MemberVO member, @Validated @ModelAttribute MemberVO updatedVO,
                               BindingResult bindingResult, HttpServletRequest request) {
        if(member == null) {
            return "redirect:/";
        }

        if(bindingResult.hasErrors()) {
            log.info("errors={}",bindingResult);
            return "redirect:/userInfo";
        }
        Member updatedMember = memberService.updateMemberInfo(updatedVO,member);
        if(updatedMember != null) {
            HttpSession session = request.getSession();
            updatedVO.setPassword(null);
            session.setAttribute(SessionConstants.LOGIN_MEMBER,updatedVO);
        }

        return "redirect:/userInfo";
    }

    @PostMapping("/delete")
    public String deleteMember(@SessionAttribute(name=SessionConstants.LOGIN_MEMBER) MemberVO member, HttpServletRequest request) {
        if(member == null) {
            return "redirect:/";
        }
        memberService.delete(member.getNickname());

        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
