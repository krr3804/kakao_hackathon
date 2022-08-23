package com.review.monitoring.MonitoringSystem.monitor.user.alarm;

import com.review.monitoring.MonitoringSystem.monitor.domain.Alarm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class AlarmRepositoryImpl implements AlarmRepository{
    private final EntityManager em;

    @Override
    public Alarm insert(Alarm alarm) {
        em.persist(alarm);
        return alarm;
    }

    @Override
    public void delete(Long alarmId) {
        Alarm alarm = em.find(Alarm.class, alarmId);
        em.remove(alarm);
    }
}
