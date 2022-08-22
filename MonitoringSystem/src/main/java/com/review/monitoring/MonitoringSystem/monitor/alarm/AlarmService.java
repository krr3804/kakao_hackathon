package com.review.monitoring.MonitoringSystem.monitor.alarm;

import com.review.monitoring.MonitoringSystem.monitor.domain.Alarm;
import com.review.monitoring.MonitoringSystem.monitor.domain.User;
import com.review.monitoring.MonitoringSystem.monitor.vo.AlarmVO;

public interface AlarmService {
    Alarm addAlarm(AlarmVO alarmVO, User user);

    void deleteAlarm(Long alarmId);
}
