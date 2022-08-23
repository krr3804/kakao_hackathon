package com.review.monitoring.MonitoringSystem.monitor.user.alarm;

import com.review.monitoring.MonitoringSystem.monitor.domain.Alarm;
import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.monitor.vo.AlarmVO;

public interface AlarmService {
    Alarm addAlarm(AlarmVO alarmVO, Member member);

    void deleteAlarm(Long alarmId, Member member);
}
