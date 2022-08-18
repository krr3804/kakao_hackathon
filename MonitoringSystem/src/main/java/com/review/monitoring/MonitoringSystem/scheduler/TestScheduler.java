package com.review.monitoring.MonitoringSystem.scheduler;
import com.review.monitoring.MonitoringSystem.elk.ElasticDocument;
import com.review.monitoring.MonitoringSystem.elk.TestElasticsearchRepository;
import com.review.monitoring.MonitoringSystem.review.Review;
import com.review.monitoring.MonitoringSystem.review.ReviewRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TestScheduler {
//    @Value("${jwt.secret}") String secret;
    private final TestElasticsearchRepository testElasticsearchRepository;
    private final ReviewRepositoryImpl reviewRepository;
    @Scheduled(fixedRate=300000)
    public void testScheduler(){
//        List<Review> reviewList = reviewRepository.getReviewData();
//        System.out.println("start"+ reviewList.size());
//        int cnt = 0;
//        for(Review r: reviewList ){
//            cnt = cnt+1;
//            if(cnt%1000==0){
//                System.out.println(cnt);
//                System.out.println(r.getDepartment());
//            }
//
//            testElasticsearchRepository.save(ElasticDocument.builder()
//                    .comment(r.getComment())
//                    .date(r.getDate())
//                    .feedback(r.getFeedback())
//                    .score(r.getScore())
//                    .department(r.getDepartment())
//                    .star(r.getStar())
//                    .mu_keyword(r.getMu_keyword())
//                    .build());
//        }
    }
}
