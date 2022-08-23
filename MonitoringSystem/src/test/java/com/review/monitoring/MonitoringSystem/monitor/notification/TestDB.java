package com.review.monitoring.MonitoringSystem.monitor.notification;

import com.review.monitoring.MonitoringSystem.monitor.domain.Department;
import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TestDB {
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

    public void init() {
        String emitterId1 = "1_" + System.currentTimeMillis();
        SseEmitter sseEmitter1 = new SseEmitter();
        emitters.put(emitterId1, sseEmitter1);
    }

    public Member findGeneralMember() {
        Member member = new Member();
        member.setId(1L);
        member.setNickname("test");
        member.setEmail("test@gmail.com");
        member.setDepartment(Department.DELIVERY);
        member.setPassword("test1234");
        return member;
    }
}
