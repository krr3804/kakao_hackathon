package com.review.monitoring.MonitoringSystem.monitor.notification;

import com.review.monitoring.MonitoringSystem.monitor.domain.Department;
import com.review.monitoring.MonitoringSystem.monitor.domain.Keyword;
import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.review.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class NotificationServiceImplTest2 {
    @Autowired
    NotificationService notificationService;
    @Autowired
    TestDB testDB;

    @BeforeEach
    void beforeEach() {
        testDB.init();
    }

    @Test
    @DisplayName("알림 구독을 진행한다.")
    public void subscribe() throws Exception {
        //given
        Member member = testDB.findGeneralMember();
        String lastEventId = "";

        //when, then
        Assertions.assertDoesNotThrow(() -> notificationService.subscribe(member.getId(), lastEventId));
    }

    @Test
    @DisplayName("알림 메세지를 전송한다.")
    public void send() throws Exception {
        //given
        Member member = testDB.findGeneralMember();
        Review review = createReview();
        String lastEventId = "";
        notificationService.subscribe(member.getId(), lastEventId);

        //when, then
        Assertions.assertDoesNotThrow(() -> notificationService.send(member,"알림 발생!", review));
        Assertions.assertEquals(notificationService.getNotifications(),1);
    }

    private Review createReview() {
        Review review = new Review();
        review.setId(2L);
        review.setStar(4);
        review.setDate(LocalDateTime.now());
        review.setDepartment(Department.DELIVERY);
        review.setFeedback(1);
        review.setScore(60);
        review.setComment("이거 별로에요!");
        review.setMu_keyword(Keyword.포장);
        return review;
    }
}