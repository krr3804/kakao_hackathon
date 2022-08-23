package com.review.monitoring.MonitoringSystem.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.monitor.notification.NotificationService;
import com.review.monitoring.MonitoringSystem.monitor.user.alarm.AlarmService;
import com.review.monitoring.MonitoringSystem.review.Review;
import com.review.monitoring.MonitoringSystem.review.ReviewService;
import com.review.monitoring.MonitoringSystem.review.ReviewServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final ReviewServiceImpl reviewService;
    private final AlarmService alarmService;
    private final NotificationService notificationService;

    @KafkaListener(topics = "complete")
    public void processMessage(String kafkaMessage) {
//        log.info("Kafka Message ====> " + kafkaMessage);
        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //리뷰 넘겨 주면 알림 보냄
        //sendNotification();
    }

    public void sendNotification(Review review) {
        List<Member> members = alarmService.getAlarmedMembers(review);
        String content = "알림 발생!";
        members.stream().forEach(member -> {
            notificationService.send(member, content, review);
        });
    }
}