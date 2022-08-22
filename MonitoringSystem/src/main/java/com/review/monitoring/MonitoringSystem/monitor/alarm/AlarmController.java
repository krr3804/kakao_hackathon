package com.review.monitoring.MonitoringSystem.monitor.alarm;

import com.review.monitoring.MonitoringSystem.monitor.domain.Alarm;
import com.review.monitoring.MonitoringSystem.monitor.domain.Department;
import com.review.monitoring.MonitoringSystem.monitor.domain.Keyword;
import com.review.monitoring.MonitoringSystem.monitor.domain.User;
import com.review.monitoring.MonitoringSystem.monitor.user.service.UserManagementService;
import com.review.monitoring.MonitoringSystem.monitor.user.session.SessionConstants;
import com.review.monitoring.MonitoringSystem.monitor.vo.AlarmVO;
import com.review.monitoring.MonitoringSystem.monitor.vo.UserVO;
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
    private final UserManagementService userManagementService;

    @GetMapping("")
    public String showAlarmPage(Model model, @SessionAttribute(name= SessionConstants.LOGIN_USER) UserVO user) {
        if(user == null) {
            return "redirect:/";
        }
        List<Alarm> alarms = userManagementService.getUser(user.getId()).getConditions();

        model.addAttribute(alarms);

        return "alarm";
    }

    @GetMapping("/add")
    public String showAddAlarmModal(Model model, @SessionAttribute(name= SessionConstants.LOGIN_USER) UserVO user) {
        if(user == null) {
            return "redirect:/";
        }

        model.addAttribute("keywords", Department.of(user.getDepartment()).getKeywords());
        return "alarmModal";
    }

    @PostMapping("/add")
    public String addAlarmCondition(@ModelAttribute AlarmVO alarmVO, @SessionAttribute(name= SessionConstants.LOGIN_USER) UserVO user) {
        if(user == null) {
            return "redirect:/";
        }
        User selectedUser = userManagementService.getUser(user.getId());

        Alarm alarm = alarmService.addAlarm(alarmVO, selectedUser);
        if(alarm != null) {
            //redirect 전에 alert 호출 필요
        }

        return "redirect:/alarm";
    }
}
