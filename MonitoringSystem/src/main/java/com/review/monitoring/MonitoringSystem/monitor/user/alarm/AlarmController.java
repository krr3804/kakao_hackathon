package com.review.monitoring.MonitoringSystem.monitor.user.alarm;

import com.review.monitoring.MonitoringSystem.monitor.domain.Alarm;
import com.review.monitoring.MonitoringSystem.monitor.domain.Department;
import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.monitor.user.service.MemberService;
import com.review.monitoring.MonitoringSystem.monitor.user.session.SessionConstants;
import com.review.monitoring.MonitoringSystem.monitor.vo.AlarmVO;
import com.review.monitoring.MonitoringSystem.monitor.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/alarm")
@Slf4j
public class AlarmController {
    private final AlarmService alarmService;
    private final MemberService memberService;

    @GetMapping("")
    public String showAlarmPage(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return "redirect:/";
        }
        UserDetails memberDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("memberDetail.getUsername() = " + memberDetail.getUsername());
        List<Alarm> alarms = memberService.getMemberByName(memberDetail.getUsername()).getAlarms();

        model.addAttribute(alarms);

        return "alarm/alarmList";
    }

    @GetMapping("add")
    public String showAddAlarmModal(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return "redirect:/";
        }
        UserDetails memberDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = memberService.getMemberByName(memberDetail.getUsername());

        model.addAttribute("alarmVO", new AlarmVO());
        model.addAttribute("keywords", member.getDepartment().getKeywords());
        return "alarm/alarmRegisterForm";
    }

    @PostMapping("add")
    public String addAlarm(@ModelAttribute(name = "alarmVO") AlarmVO alarmVO) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return "redirect:/";
        }
        UserDetails memberDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = memberService.getMemberByName(memberDetail.getUsername());

        Alarm alarm = alarmService.addAlarm(alarmVO, member);
        if(alarm != null) {
            System.out.println("alarm = " + alarm.getId());
        }

        return "redirect:/alarm";
    }

    @GetMapping("delete/{id}")
    public String deleteAlarm(@PathVariable("id") Long id) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return "redirect:/";
        }
        UserDetails memberDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member selectedMember = memberService.getMemberByName(memberDetail.getUsername());
        alarmService.deleteAlarm(id,selectedMember);
        return "redirect:/alarm";
    }
}
