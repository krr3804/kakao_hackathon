package com.review.monitoring.MonitoringSystem.monitor.alarm;

import com.review.monitoring.MonitoringSystem.monitor.domain.Alarm;
import com.review.monitoring.MonitoringSystem.monitor.domain.Department;
import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.monitor.user.service.MemberService;
import com.review.monitoring.MonitoringSystem.monitor.user.session.SessionConstants;
import com.review.monitoring.MonitoringSystem.monitor.vo.AlarmVO;
import com.review.monitoring.MonitoringSystem.monitor.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public String showAlarmPage(Model model, @SessionAttribute(name= SessionConstants.LOGIN_MEMBER) MemberVO user) {
        if(user == null) {
            return "redirect:/";
        }
        List<Alarm> alarms = memberService.getMember(user.getId()).getAlarms();

        model.addAttribute(alarms);

        return "alarm/alarmList";
    }

    @GetMapping("add")
    public String showAddAlarmModal(Model model, @SessionAttribute(name= SessionConstants.LOGIN_MEMBER) MemberVO member) {
        if(member == null) {
            return "redirect:/";
        }
        model.addAttribute("form", new AlarmVO());
        model.addAttribute("keywords", Department.of(member.getDepartment()).getKeywords());
        return "alarm/alarmRegisterForm";
    }

    @PostMapping("add")
    public String addAlarm(@ModelAttribute(name = "alarmVO") AlarmVO alarmVO,
                           @SessionAttribute(name= SessionConstants.LOGIN_MEMBER) MemberVO member) {
        if(member == null) {
            return "redirect:/";
        }
        Member selectedMember = memberService.getMember(member.getId());

        Alarm alarm = alarmService.addAlarm(alarmVO, selectedMember);
        if(alarm != null) {
            //redirect 전에 alert 호출 필요
        }

        return "redirect:/alarm";
    }

    @GetMapping("delete/{id}")
    public String deleteAlarm(@PathVariable("id") Long id,
                              @SessionAttribute(name= SessionConstants.LOGIN_MEMBER) MemberVO member) {
        Member selectedMember = memberService.getMember(member.getId());
        alarmService.deleteAlarm(id,selectedMember);
        return "redirect:/alarm";
    }
}
