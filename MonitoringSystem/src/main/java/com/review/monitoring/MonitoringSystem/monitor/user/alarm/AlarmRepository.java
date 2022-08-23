package com.review.monitoring.MonitoringSystem.monitor.user.alarm;

import com.review.monitoring.MonitoringSystem.monitor.domain.Alarm;

public interface AlarmRepository {
    Alarm insert(Alarm alarm);

    void delete(Long alarmId);
}
