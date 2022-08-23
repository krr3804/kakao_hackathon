package com.review.monitoring.MonitoringSystem.monitor.user.controller;

import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.monitor.user.service.MemberService;
import com.review.monitoring.MonitoringSystem.monitor.user.session.SessionConstants;
import com.review.monitoring.MonitoringSystem.monitor.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public String showMemberPage(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return "redirect:/";
        }

        return "member";
    }

    @GetMapping("/memberInfo")
    public String showMemberInfo(Member member, Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return "redirect:/";
        }
        UserDetails memberDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("memberDetail", memberDetail);

        return "memberInfo";
    }

    @PostMapping("/edit")
    public String updateMember(@Validated @ModelAttribute MemberVO updatedVO,
                               BindingResult bindingResult) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return "redirect:/";
        }

        if(bindingResult.hasErrors()) {
            log.info("errors={}",bindingResult);
            return "redirect:/member/memberInfo";
        }
        UserDetails memberDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = memberService.getMemberByName(memberDetail.getUsername());

        Member updatedMember = memberService.updateMemberInfo(updatedVO,member);


        return "redirect:/member/memberInfo";
    }

    @PostMapping("/delete")
    public String deleteMember(HttpServletRequest request) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return "redirect:/";
        }
        UserDetails memberDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = memberService.getMemberByName(memberDetail.getUsername());

        memberService.delete(member.getId());

        return "redirect:/logout";
    }
}
