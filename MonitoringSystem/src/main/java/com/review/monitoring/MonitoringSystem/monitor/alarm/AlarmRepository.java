package com.review.monitoring.MonitoringSystem.monitor.alarm;

import com.review.monitoring.MonitoringSystem.monitor.domain.Alarm;
import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.review.Review;

import java.util.List;
import java.util.Map;

public interface AlarmRepository {
    Alarm insert(Alarm alarm);

    List<Member> selectUsers(Review review);

    void delete(Long alarmId);
}
