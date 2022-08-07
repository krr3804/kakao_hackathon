package com.review.monitoring.MonitoringSystem.elk;

import com.review.monitoring.MonitoringSystem.review.Review;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
public interface TestElasticsearchRepository extends ElasticsearchRepository<ElasticDocument,Long> {
}
