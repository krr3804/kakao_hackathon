package com.review.monitoring.MonitoringSystem.monitor.notification;

import com.review.monitoring.MonitoringSystem.monitor.domain.Department;
import com.review.monitoring.MonitoringSystem.monitor.domain.Keyword;
import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.review.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class NotificationServiceImplTest {
    private EmitterRepository emitterRepository = new EmitterRepositoryImpl();
    private Long DEFAULT_TIMEOUT = 60L * 1000L * 60L;

    @Test
    @DisplayName("새로운 Emitter를 추가한다.")
    public void save() throws Exception {
        //given
        Long memberId = 1L;
        String emitterId =  memberId + "_" + System.currentTimeMillis();
        SseEmitter sseEmitter = new SseEmitter(DEFAULT_TIMEOUT);

        //when, then
        Assertions.assertDoesNotThrow(() -> emitterRepository.save(emitterId, sseEmitter));
    }

    @Test
    @DisplayName("수신한 이벤트를 캐시에 저장한다.")
    public void saveEventCache() throws Exception {
        //given
        Member member = new Member();
        member.setId(1L);
        Review review = new Review();
        review.setId(2L);
        review.setStar(4);
        review.setDate(LocalDateTime.now());
        review.setDepartment(Department.DELIVERY);
        review.setFeedback(1);
        review.setScore(60);
        review.setComment("이거 별로에요!");
        review.setMu_keyword(Keyword.포장);
        String eventCacheId =  member.getId() + "_" + System.currentTimeMillis();
        Notification notification = new Notification(member, "알람!",review, "url", false);

        //when, then
        Assertions.assertDoesNotThrow(() -> emitterRepository.saveEventCache(eventCacheId, notification));
    }

    @Test
    @DisplayName("어떤 회원이 접속한 모든 Emitter를 찾는다")
    public void findAllEmitterStartWithByMemberId() throws Exception {
        //given
        Long memberId = 1L;
        String emitterId1 = memberId + "_" + System.currentTimeMillis();
        emitterRepository.save(emitterId1, new SseEmitter(DEFAULT_TIMEOUT));

        Thread.sleep(100);
        String emitterId2 = memberId + "_" + System.currentTimeMillis();
        emitterRepository.save(emitterId2, new SseEmitter(DEFAULT_TIMEOUT));

        Thread.sleep(100);
        String emitterId3 = memberId + "_" + System.currentTimeMillis();
        emitterRepository.save(emitterId3, new SseEmitter(DEFAULT_TIMEOUT));


        //when
        Map<String, SseEmitter> ActualResult = emitterRepository.findAllEmitterStartWithByMemberId(String.valueOf(memberId));

        //then
        Assertions.assertEquals(3, ActualResult.size());
    }

    @Test
    @DisplayName("어떤 회원에게 수신된 이벤트를 캐시에서 모두 찾는다.")
    public void findAllEventCacheStartWithByMemberId() throws Exception {
        //given
        Long memberId = 1L;
        String eventCacheId1 =  memberId + "_" + System.currentTimeMillis();
        Notification notification1 = new Notification(new Member(1L), NotificationType.APPLY, "스터디 신청이 왔습니다.", "url", false);
        emitterRepository.saveEventCache(eventCacheId1, notification1);

        Thread.sleep(100);
        String eventCacheId2 =  memberId + "_" + System.currentTimeMillis();
        Notification notification2 = new Notification(new Member(1L), NotificationType.ACCEPT, "스터디 신청이 승인되었습니다.", "url", false);
        emitterRepository.saveEventCache(eventCacheId2, notification2);

        Thread.sleep(100);
        String eventCacheId3 =  memberId + "_" + System.currentTimeMillis();
        Notification notification3 = new Notification(new Member(1L), NotificationType.REJECT, "스터디 신청이 거절되었습니다.", "url", false);
        emitterRepository.saveEventCache(eventCacheId3, notification3);

        //when
        Map<String, Object> ActualResult = emitterRepository.findAllEventCacheStartWithByMemberId(String.valueOf(memberId));

        //then
        Assertions.assertEquals(3, ActualResult.size());
    }

    @Test
    @DisplayName("ID를 통해 Emitter를 Repository에서 제거한다.")
    public void deleteById() throws Exception {
        //given
        Long memberId = 1L;
        String emitterId =  memberId + "_" + System.currentTimeMillis();
        SseEmitter sseEmitter = new SseEmitter(DEFAULT_TIMEOUT);

        //when
        emitterRepository.save(emitterId, sseEmitter);
        emitterRepository.deleteById(emitterId);

        //then
        Assertions.assertEquals(0, emitterRepository.findAllEmitterStartWithByMemberId(emitterId).size());
    }

    @Test
    @DisplayName("저장된 모든 Emitter를 제거한다.")
    public void deleteAllEmitterStartWithId() throws Exception {
        //given
        Long memberId = 1L;
        String emitterId1 = memberId + "_" + System.currentTimeMillis();
        emitterRepository.save(emitterId1, new SseEmitter(DEFAULT_TIMEOUT));

        Thread.sleep(100);
        String emitterId2 = memberId + "_" + System.currentTimeMillis();
        emitterRepository.save(emitterId2, new SseEmitter(DEFAULT_TIMEOUT));

        //when
        emitterRepository.deleteAllEmitterStartWithId(String.valueOf(memberId));

        //then
        Assertions.assertEquals(0, emitterRepository.findAllEmitterStartWithByMemberId(String.valueOf(memberId)).size());
    }

    @Test
    @DisplayName("수신한 이벤트를 캐시에 저장한다.")
    public void deleteAllEventCacheStartWithId() throws Exception {
        //given
        Long memberId = 1L;
        String eventCacheId1 =  memberId + "_" + System.currentTimeMillis();
        Notification notification1 = new Notification(new Member(1L), NotificationType.APPLY, "스터디 신청이 왔습니다.", "url", false);
        emitterRepository.saveEventCache(eventCacheId1, notification1);

        Thread.sleep(100);
        String eventCacheId2 =  memberId + "_" + System.currentTimeMillis();
        Notification notification2 = new Notification(new Member(1L), NotificationType.ACCEPT, "스터디 신청이 승인되었습니다.", "url", false);
        emitterRepository.saveEventCache(eventCacheId2, notification2);

        //when
        emitterRepository.deleteAllEventCacheStartWithId(String.valueOf(memberId));

        //then
        Assertions.assertEquals(0, emitterRepository.findAllEventCacheStartWithByMemberId(String.valueOf(memberId)).size());
    }
}