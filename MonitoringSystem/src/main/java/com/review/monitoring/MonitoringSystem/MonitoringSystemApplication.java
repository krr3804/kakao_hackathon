package com.review.monitoring.MonitoringSystem;

import com.review.monitoring.MonitoringSystem.elk.TestElasticsearchRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories("com.review.monitoring.MonitoringSystem.review")
@EnableElasticsearchRepositories(basePackages = "com.review.monitoring.MonitoringSystem.elk")
public class MonitoringSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringSystemApplication.class, args);
	}

}
