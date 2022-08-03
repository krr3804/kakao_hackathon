package com.review.monitoring.MonitoringSystem.scheduler;

import com.review.monitoring.MonitoringSystem.elk.ElasticDocument;
import com.review.monitoring.MonitoringSystem.elk.TestElasticsearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@RequiredArgsConstructor
public class TestScheduler {
    private final TestElasticsearchRepository testElasticsearchRepository;
    @Scheduled(fixedDelay=3000)
    public void testScheduler(){
        System.out.println("Scheduler");
        ElasticDocument entity = new ElasticDocument();
        testElasticsearchRepository.save(entity);
    }
}
