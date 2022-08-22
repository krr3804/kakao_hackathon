package com.review.monitoring.MonitoringSystem.monitor.alarm;

import com.review.monitoring.MonitoringSystem.monitor.domain.Alarm;
import com.review.monitoring.MonitoringSystem.monitor.domain.Department;
import com.review.monitoring.MonitoringSystem.monitor.domain.Keyword;
import com.review.monitoring.MonitoringSystem.monitor.domain.User;
import com.review.monitoring.MonitoringSystem.monitor.user.repository.UserManagementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class AlarmControllerTest {
    private UserManagementRepository userManagementRepository;
    private AlarmRepository alarmRepository;

    @Autowired
    public AlarmControllerTest(UserManagementRepository userManagementRepository, AlarmRepository alarmRepository) {
        this.userManagementRepository = userManagementRepository;
        this.alarmRepository = alarmRepository;
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void addAlarmCondition() {
//        userManagementRepository.insertUser(new User("test","test12345","test@gmail.com",Department.DELIVERY_DEPT));
        User user = userManagementRepository.selectOne("test");
        Alarm alarm = new Alarm(1L,70L, Keyword.ARRIVAL,user);
        Long alarmId = alarmRepository.insert(alarm);

        Assertions.assertNotNull(alarmId);
        Assertions.assertFalse(user.getConditions().isEmpty());
    }
}