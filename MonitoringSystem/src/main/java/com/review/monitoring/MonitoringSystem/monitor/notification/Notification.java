package com.review.monitoring.MonitoringSystem.monitor.notification;

import com.review.monitoring.MonitoringSystem.monitor.domain.Alarm;
import com.review.monitoring.MonitoringSystem.monitor.domain.Member;
import com.review.monitoring.MonitoringSystem.review.Review;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Notification{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @Getter
    @Embedded
    private NotificationContent content;


    @Getter
    @Embedded
    private Review review;

    @Getter
    @Embedded
    private RelatedUrl url;
    @Column(nullable = false)
    private Boolean isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member receiver;

    @Builder
    public Notification(Member receiver, String content, Review review, String url, Boolean isRead) {
        this.receiver = receiver;
        this.content = new NotificationContent(content);
        this.review = review;
        this.url = new RelatedUrl(url);
        this.isRead = isRead;
    }
}
