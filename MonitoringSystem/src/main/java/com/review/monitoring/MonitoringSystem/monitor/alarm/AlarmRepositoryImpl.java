package com.review.monitoring.MonitoringSystem.monitor.alarm;

import com.review.monitoring.MonitoringSystem.monitor.domain.Alarm;
import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

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
    public List<Member> selectUsers(Review review) {
        return null;
    }


    @Override
    public void delete(Long alarmId) {
        Alarm alarm = em.find(Alarm.class, alarmId);
        em.remove(alarm);
    }
}
