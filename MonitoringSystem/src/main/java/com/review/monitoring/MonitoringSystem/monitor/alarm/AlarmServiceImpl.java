package com.review.monitoring.MonitoringSystem.monitor.alarm;

import com.review.monitoring.MonitoringSystem.monitor.domain.Alarm;
import com.review.monitoring.MonitoringSystem.monitor.domain.Keyword;
import com.review.monitoring.MonitoringSystem.monitor.domain.User;
import com.review.monitoring.MonitoringSystem.monitor.vo.AlarmVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService{

    private final AlarmRepository alarmRepository;

    @Override
    public Alarm addAlarm(AlarmVO alarmVO, User user) {
        if(user.getConditions().stream().anyMatch(alarm -> alarm.getFeedback().equals(alarmVO.getFeedback())
                && alarm.getScore().equals(alarmVO.getScore())
                && alarm.getKeyword().equals(Keyword.of(alarmVO.getKeyword())))) {
            return null;
        }
        Alarm alarm = new Alarm(alarmVO.getFeedback(), alarmVO.getScore(),
                Keyword.of(alarmVO.getKeyword()),user);
        return alarmRepository.insert(alarm);
    }

    @Override
    public void deleteAlarm(Long alarmId) {

    }


}
